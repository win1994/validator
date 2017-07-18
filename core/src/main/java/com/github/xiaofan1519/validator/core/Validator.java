package com.github.xiaofan1519.validator.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.github.xiaofan1519.validator.annotation.handle.Handle;
import com.github.xiaofan1519.validator.exception.ValidatorException;
import com.github.xiaofan1519.validator.handle.ValidatorHandle;

/**
 * @author XiaoFan
 *
 */
public abstract class Validator
{
    private static final Logger LOGGER = Logger.getLogger(Validator.class);

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
            throw new ValidatorException(null, error);
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
            catch (ValidatorException e)
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
            throw new ValidatorException("无法获取到该字段的值", e);
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
            throw new ValidatorException("校验注解没有默认的 name 字段", e);
        }

        // 获取注解中的handle
        ValidatorHandle verifyHandle = getValidatorHandle(handleAnnotation.value());

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
            throw new ValidatorException(e, error);
        }
    }

    /**
     * 获取ValidatorHandle的实例
     * 
     * @param clazz
     * @return
     */
    private static ValidatorHandle getValidatorHandle(Class<? extends ValidatorHandle> clazz)
    {
        Constructor<? extends ValidatorHandle> constructor;
        ValidatorHandle verifyHandle = null;

        // TODO 暂时没性能要求，不缓存实例对象
        try
        {
            constructor = clazz.getConstructor();
            verifyHandle = constructor.newInstance();
        }
        catch (Exception e)
        {
            LOGGER.error("校验器内部错误:" + clazz.getSimpleName(), e);

            throw new ValidatorException("实例化:" + clazz.getSimpleName() + " 错误", e);
        }

        return verifyHandle;
    }
    
    /**
     * 静态方法校验
     */
    
    /**
     * 当 field 为null时，抛出异常
     * 
     * @param field 要校验的字段
     */
    public static void isNull(Object field)
    {
    	isNull(field, null);
    }
    
    /**
     * 当 field 为null时，抛出异常
     * 
     * @param field 要校验的字段
     * @param msg 自定义错误提示信息
     */
    public static void isNull(Object field, String msg)
    {
    	if (null != field)
    	{
    		return;
    	}
    	
    	if (null == msg) 
    	{
    		throw new ValidatorException("字段不能为空");
    	}
    	
    	throw new ValidatorException(msg);
    }
    
    /**
     * 当 field 为null或者空时，返回true
     * 
     * @param field 被校验的字段
     */
    private static boolean empty(CharSequence field)
    {
    	if (null == field || field.length() == 0) {
    		return true;
    	}
    	
    	return false;
    }
    
    /**
     * 当 field 为null或者空时，抛出异常
     * 
     * @param field 被校验的字段
     */
    public static void isEmpty(CharSequence field)
    {
    	isNull(field);
    	isEmpty(field, null);
    }
    
    /**
     * 当 field 为null或者空时，抛出异常
     * 
     * @param field 被校验的字段
     * @param msg 自定义错误提示信息
     */
    public static void isEmpty(CharSequence field, String msg)
    {
    	isNull(field);
    	if (field.length() == 0)
    	{
    		if (null == msg) {    			
    			throw new ValidatorException("字段不能为空");
    		}
    		throw new ValidatorException(msg);
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
    	isNull(field);
    	int length = field.length();
    	if (length < min || length > max)
    	{
    		throw new ValidatorException("值 " + field + " 长度不合法.最小:" + min + "最大:" + max);
    	}
    }
    
    /**
     * 当 field 的长度不在指定范围内，抛出异常
     * 该方法允许字段值为null 或 空字符串，适用于非必填字段校验
     * 
     * @param field 要校验的字段
     * @param min 最小长度
     * @param max 最大长度
     */
    public static void verifyLenAllowEmpty(CharSequence field, int min, int max)
    {
    	if (empty(field)) {
    		return ;
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
    	isNull(field);
    	for (CharSequence charSequence : enums) {
			if (field.equals(charSequence))
			{
				return;
			}
		}
    	throw new ValidatorException("参数 field 值 枚举校验失败");
    }
    
    /**
     * 当 field 不等于任何一个枚举值，抛出异常
     * 该方法允许字段值为null 或 空字符串，适用于非必填字段校验
     * 
     * @param field 要校验的字段
     * @param enums 枚举值
     */
    public static void verifyEnumAllowEmpty(CharSequence field, CharSequence... enums)
    {
    	if (empty(field)) {
    		return ;
    	}
    	
    	verifyEnum(field, enums);
    }
    
    /**
     * 校验正则表达式
     * @param field 要校验的字段
     * @param regEx 正则表达式
     */
    public static void verifyRegEx(CharSequence field, String regEx)
    {
    	Matcher matcher = null;
    	try
    	{
    		Pattern pattern = Pattern.compile(regEx);
    		matcher = pattern.matcher(field);
    	}
    	catch (RuntimeException e)
    	{
    		throw new ValidatorException(e);
    	}
        
        if (!matcher.matches())
        {
        	throw new ValidatorException("正则表达式不匹配");
        }
    }
    
    /**
     * 校验邮箱格式
     * @param field 要校验的字段
     */
    public static void verifyEmail(CharSequence field)
    {
    	isNull(field);
    	
    	String regEx = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
    	verifyRegEx(field, regEx);
    }
    
    /**
     * 校验邮箱格式
     * 该方法允许字段值为null 或 空字符串，适用于非必填字段校验
     * @param field 要校验的字段
     */
    public static void verifyEmailAllowEmpty(CharSequence field)
    {
    	if (empty(field)) {
    		return ;
    	}
    	
    	verifyEmail(field);
    }
    
    /**
     * 校验是否是合法的数字（负数，小数）
     * @param field
     */
    public static void verifyNum(CharSequence field)
    {
    	isNull(field);
    	
    	try {
    		new BigDecimal(field.toString());
    	}
    	catch (NumberFormatException e) {
    		throw new ValidatorException("数字非法");
		}
    }
    
    /**
     * 校验是否是合法的数字（负数，小数，科学计数）
     * 该方法允许字段值为null 或 空字符串，适用于非必填字段校验
     * @param field
     */
    public static void verifyNumAllowEmpty(CharSequence field)
    {
    	if (empty(field)) {
    		return ;
    	}
    	
    	try {
    		Integer.valueOf(field.toString());
    	}
    	catch (NumberFormatException e) {
    		throw new ValidatorException("数字非法");
		}
    }
    
    /**
     * 校验是否是整数
     * @param field
     */
    public static void isDigits(CharSequence field)
    {
    	isDigits(field, null);
    }
    
    /**
     * 校验是否是整数
     * @param field
     * @param msg 自定义错误提示信息
     */
    public static void isDigits(CharSequence field, String msg)
    {
    	isEmpty(field);
    	
    	for (int i = 0; i < field.length(); i++) {
			if (!Character.isDigit(field.charAt(i))) {
				if (null == msg) {
					throw new ValidatorException("字段值不是一个有效的整数");					
				}
				
				throw new ValidatorException(msg);
			}
		}
    }
    
    /**
     * 校验是否是整数
     * 该方法允许字段值为null 或 空字符串，适用于非必填字段校验
     * @param field
     */
    public static void isDigitsAllowEmpty(CharSequence field)
    {
    	if (empty(field))
    	{
    		return ;
    	}
    	
    	isDigits(field);
    }
}