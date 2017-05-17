package com.github.xiaofan1519.verify.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.github.xiaofan1519.verify.annotation.handle.Handle;
import com.github.xiaofan1519.verify.exception.VerifyException;
import com.github.xiaofan1519.verify.handle.VerifyHandle;

/**
 * @author XiaoFan
 *
 */
public abstract class Verify
{
    private static final Logger LOGGER = Logger.getLogger(Verify.class);

    /**
     * 校验方法
     * 
     * @param bean
     *            要被校验的类
     */
    public static final void verify(Object bean)
    {
        Map<String, String> error = new LinkedHashMap<>();
        verify(bean, error);

        // error 不为空
        if (error.size() != 0)
        {
            throw new VerifyException(null, error);
        }
    }

    /**
     * 内部的校验方法
     * 
     * @param bean
     *            要被校验的类
     * @param error
     *            字段不匹配时的错误提示
     */
    private static void verify(Object bean, Map<String, String> error)
    {
        if (null == bean)
        {
            return;
        }

        Class<?> clazz = bean.getClass();

        // 获取所有的字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields)
        {
            Object value = null;
            try
            {
                value = getFieldValue(bean, field, error);
            }
            catch (VerifyException e)
            {
                continue;
            }

            // 获取字段上的注解
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations)
            {
                verifyHandle(annotation, field.getName(), value, error);
            }

            /*// 字段内容为null
            if (value == bean)
            {
                continue;
            }*/
            
            // TODO 这里会造成 stackoverflow，暂时不校验类中类
            /*// 根据类型选择对应的校验方式
            if (value instanceof Map<?, ?>)
            {
                verifyMap((Map<?, ?>) value, error);
            }
            else if (value instanceof Collection<?>)
            {
                validateCollection((Collection<?>) value, error);
            }
            else
            {
                verify(value, error);
            }*/
        }
    }

    /**
     * 校验集合中的字段
     * 
     * @param map
     * @param error
     */
    /*private static <K, V> void verifyMap(Map<K, V> map, Map<String, String> error)
    {
        Set<K> keys = map.keySet();
        Iterator<K> iter = keys.iterator();
        while (iter.hasNext())
        {

            Object key = iter.next();
            verify(key, error);

            Object value = map.get(key);
            verify(value, error);
        }
    }*/

    /**
     * 校验集合中的字段
     * 
     * @param collection
     * @param error
     */
    /*private static <T> void validateCollection(Collection<T> collection, Map<String, String> error)
    {
        Iterator<?> iter = collection.iterator();
        while (iter.hasNext())
        {
            Object value = iter.next();
            verify(value, error);
        }
    }*/

    /**
     * 获取字段中的值
     * 
     * @param field
     */
    private static Object getFieldValue(Object bean, Field field, Map<String, String> error)
    {
        Object value = null;
        
        try
        {
            // 禁用安全检查
            field.setAccessible(true);
            value = field.get(bean);
        }
        catch (IllegalAccessException e)
        {
            LOGGER.error("无法获取到该字段的值", e);
            throw new VerifyException("无法获取到该字段的值", e);
        }
        finally
        {
            // 还原
            field.setAccessible(false);
        } 

        return value;
    }

    /**
     * 调用对应的handle来进行校验
     * 
     * @param annotation
     * @param fieldName
     * @param error
     */
    private static void verifyHandle(Annotation annotation, String fieldName, Object value, Map<String, String> error)
    {
        // 注解的Class
        Class<? extends Annotation> annotationClass = annotation.annotationType();

        // 获取校验器注解
        Handle handleAnnotation = annotationClass.getDeclaredAnnotation(Handle.class);

        // 如果没有说明不是校验注解
        if (null == handleAnnotation)
        {
            return;
        }

        // 当前字段校验不通过时的提示信息
        String errorTip = null;

        try
        {
            Method nameField = annotationClass.getMethod("name");
            errorTip = (String) nameField.invoke(annotation);
        }
        catch (Exception e)
        {
            LOGGER.error("校验注解没有默认的 name 字段" + annotationClass.getSimpleName(), e);
            throw new VerifyException("校验注解没有默认的 name 字段", e);
        }

        // 获取注解中的handle
        VerifyHandle verifyHandle = getValidatorHandle(handleAnnotation.value());

        try
        {
            // 初始化并校验
            verifyHandle.initialize(annotation);
            if (!verifyHandle.handle(value))
            {
                error.put(fieldName, errorTip);
            }
        }
        catch (Exception e)
        {
            error.put(fieldName, e.getMessage());
            LOGGER.error("Handle 抛出异常" + verifyHandle.getClass().getSimpleName(), e);
            throw new VerifyException(e, error);
        }
    }

    /**
     * 获取ValidatorHandle的实例
     * 
     * @param clazz
     * @return
     */
    private static VerifyHandle getValidatorHandle(Class<? extends VerifyHandle> clazz)
    {
        Constructor<? extends VerifyHandle> constructor;
        VerifyHandle verifyHandle = null;

        // TODO 暂时没性能要求，不缓存实例对象
        try
        {
            constructor = clazz.getConstructor();
            verifyHandle = constructor.newInstance();
        }
        catch (Exception e)
        {
            LOGGER.error("校验器内部错误:" + clazz.getSimpleName(), e);

            throw new VerifyException("实例化:" + clazz.getSimpleName() + " 错误", e);
        }

        return verifyHandle;
    }
    
    /**
     * 当 field 为null时，抛出异常
     * 
     * @param field 要校验的字段
     */
    public static void verifyNull(Object field)
    {
    	if (null == field)
    	{
    		throw new VerifyException("参数 field 值为null");
    	}
    }
    
    /**
     * 当 field 为null或者空时，抛出异常
     * 
     * @param field
     */
    public static void verifyEmpty(CharSequence field)
    {
    	verifyNull(field);
    	if (field.length() == 0)
    	{
    		throw new VerifyException("参数 field 值为空");
    	}
    }
    
    /**
     * 当 field 的长度不在指定范围内，抛出异常
     * 
     * @param field 要校验的字段
     * @param min 最小长度
     * @param max 最大长度
     */
    public static void verifyLen(CharSequence field, int min, int max)
    {
    	verifyNull(field);
    	int length = field.length();
    	if (length < min || length > max)
    	{
    		throw new VerifyException("参数 field 值 长度不合法");
    	}
    }
    
    /**
     * 当 field 的长度不在指定范围内，抛出异常
     * 该方法允许字段值为null，适用于非必填字段校验
     * 
     * @param field 要校验的字段
     * @param min 最小长度
     * @param max 最大长度
     */
    public static void verifyLenAllowNull(CharSequence field, int min, int max)
    {
    	if (null == field)
    	{
    		return;
    	}
    	
    	verifyLen(field, min, max);
    }
    
    /**
     * 当 field 不等于任何一个枚举值，抛出异常
     * 
     * @param field 要校验的字段
     * @param enums 枚举值
     */
    public static void verifyEnum(CharSequence field, CharSequence... enums)
    {
    	verifyNull(field);
    	for (CharSequence charSequence : enums) {
			if (field.equals(charSequence))
			{
				return;
			}
		}
    	throw new VerifyException("参数 field 值 枚举校验失败");
    }
    
    /**
     * 当 field 不等于任何一个枚举值，抛出异常
     * 该方法允许字段值为null，适用于非必填字段校验
     * 
     * @param field 要校验的字段
     * @param enums 枚举值
     */
    public static void verifyEnumAllowNull(CharSequence field, CharSequence... enums)
    {
    	if (null == field)
    	{
    		return;
    	}
    	
    	verifyEnum(field, enums);
    }
}
