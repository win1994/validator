package org.fan.verify.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.fan.verify.annotation.Handle;
import org.fan.verify.exception.VerifyException;
import org.fan.verify.handle.VerifyHandle;

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
     * @param bean 要被校验的类
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
     * @param bean 要被校验的类
     * @param error 字段不匹配时的错误提示
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
            
            // 字段内容为null
            if (value == bean)
            {
                continue;
            }
            
            // 根据类型选择对应的校验方式
            if (value instanceof Map<?, ?>)
            {
                verifyMap((Map<?, ?>)value, error);
            }
            else if (value instanceof Collection<?>)
            {
                validateCollection((Collection<?>) value, error);
            }
            else
            {
                verify(value, error);
            }
        }
    }

    /**
     * 校验集合中的字段
     * @param collection
     * @param error
     */
    private static <K, V> void verifyMap(Map<K, V> map, Map<String, String> error)
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
    }
    
    /**
     * 校验集合中的字段
     * @param collection
     * @param error
     */
    private static <T> void validateCollection(Collection<T> collection, Map<String, String> error)
    {
        Iterator<?> iter = collection.iterator();
        while (iter.hasNext())
        {
            Object value = iter.next();
            verify(value, error);
        }
    }

    /**
     * 获取字段中的值
     * 
     * @param field
     */
    private static Object getFieldValue(Object bean, Field field, Map<String, String> error)
    {
        int mod = field.getModifiers();
        Object value = null;

        // 根据访问权限来获取字段值
        if (Modifier.isPublic(mod))
        {
            value = getPublicFieldValue(bean, field, error);
        }
        else if (Modifier.isPrivate(mod))
        {
            value = getPrivateFieldValue(bean, field, error);
        }
        else
        {
            LOGGER.debug("无法获取到该字段的值，可能是没有 public、private 权限");
            throw new VerifyException();
        }

        return value;
    }

    /**
     * 获取访问类型为public的字段的值
     * 
     * @param bean
     * @param field
     * @return
     */
    private static Object getPublicFieldValue(Object bean, Field field, Map<String, String> error)
    {
        Object value = null;
        try
        {
            value = field.get(bean);
        }
        catch (Exception e)
        {
            LOGGER.debug("无法获取到该字段的值", e);
            ;
            throw new VerifyException(e);
        }
        return value;
    }

    /**
     * 获取访问类型为private的字段的值
     * 
     * @param bean
     * @param field
     * @param error
     * @return
     */
    private static Object getPrivateFieldValue(Object bean, Field field, Map<String, String> error)
    {
        Object value = null;
        String methodName = getMethodNameByField(field.getName());

        try
        {
            Method method = bean.getClass().getMethod(methodName);
            value = method.invoke(bean);
        }
        catch (Exception e)
        {
            LOGGER.debug("该字段没有对应的get方法", e);
            throw new VerifyException(e);
        }

        return value;
    }

    /**
     * 根据字段名获取对应的get方法
     * 
     * @param name
     * @return
     */
    private static String getMethodNameByField(String name)
    {
        StringBuffer buff = new StringBuffer(name);
        
        buff.setCharAt(0, Character.toUpperCase(buff.charAt(0)));
        buff.insert(0, "get");
        return buff.toString();
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
        // 获取校验器注解
        Handle handleAnnotation = annotation.annotationType().getDeclaredAnnotation(Handle.class);
        
        if (null == handleAnnotation)
        {
            return;
        }

        // 获取注解中的handle
        VerifyHandle verifyHandle = getValidatorHandle(handleAnnotation.handle());
        
        StringBuffer errorTip = new StringBuffer();

        try
        {
            if (!verifyHandle.handle(annotation, value, errorTip))
            {
                error.put(fieldName, errorTip.toString());
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
}
