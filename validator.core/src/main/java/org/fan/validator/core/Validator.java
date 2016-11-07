/**
 * 
 */
package org.fan.validator.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fan.validator.annotation.Handle;
import org.fan.validator.exception.ValidatorException;
import org.fan.validator.handle.ValidatorHandle;

/**
 * @author XiaoFan
 *
 */
public abstract class Validator {
    
    private static final Logger LOGGER = Logger.getLogger(Validator.class);
    
    /**
     * 校验方法
     * @param bean 要被校验的类
     */
    public static final void validate(Object bean)
    {
        // key = 不符合校验规则的字段名 value = 不符合校验规则的描述信息
        Map<String, String> error = new LinkedHashMap<>();
        validate(bean, error);
        
        // 说明有校验错误
        if (error.size() != 0)
        {
            throw new ValidatorException(error);
        }
    }
    
    /**
     * 内部的校验方法
     * @param bean 要被校验的类
     * @param error 字段不匹配时的错误提示
     */
    private static void validate(Object bean , Map<String, String> error)
    {
        Class<?> clazz = bean.getClass();
        // 获取该类所有的字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            
            Object value = null;
            try
            {
                value = getFieldValue(bean, field, error);
            }
            catch (ValidatorException e)
            {
                continue;
            }
            
            // 获取该类字段上的注解
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                validateHandle(annotation, field.getName(), value, error);
            }
        }
    }
    
    /**
     * 获取字段中的值
     * @param field
     */
    private static Object getFieldValue(Object bean, Field field , Map<String, String> error)
    {
        int mod = field.getModifiers();
        Object value = null;
        
        // 判断访问权限
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
            error.put(field.getName(), "无法获取到该字段的值，可能是没有 public、private 权限");
        }
        
        return value;
    }
    
    /** 获取访问类型为public的字段的值
     * @param bean
     * @param field
     * @return
     */
    private static Object getPublicFieldValue(Object bean, Field field , Map<String, String> error)
    {
        Object value = null;
        try
        {
            value = field.get(bean);
        }
        catch (Exception e)
        {
            LOGGER.debug("无法获取到该字段的值", e);
            error.put(field.getName(), "无法获取到该字段的值");
            throw new ValidatorException(e);
        }
        return value;
    }
    
    /**
     * 获取访问类型为private的字段的值
     * @param bean
     * @param field
     * @param error
     * @return
     */
    private static Object getPrivateFieldValue(Object bean, Field field , Map<String, String> error)
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
            error.put(field.getName(), "该字段没有对应的get方法");
            throw new ValidatorException(e);
        }
        
        return value;
    }
    
    /**
     * 根据字段名获取对应的get方法
     * @param name
     * @return
     */
    private static String getMethodNameByField(String name)
    {
        StringBuffer buff = new StringBuffer(name);
        // 首字母大写
        buff.setCharAt(0, Character.toUpperCase(buff.charAt(0)));
        buff.insert(0, "get");
        return buff.toString();
    }
    
    /** 调用对应的handle来进行校验
     * @param annotation
     * @param fieldName
     * @param error
     */
    private static void validateHandle(Annotation annotation, String fieldName , Object value, Map<String, String> error)
    {
        Handle handleAnnotation = annotation.annotationType().getDeclaredAnnotation(Handle.class);
        
        if (null == handleAnnotation)
        {
            LOGGER.error("没有 Handle 注解:" + 
                    annotation.getClass().getSimpleName());
            throw new ValidatorException(annotation.getClass().getSimpleName() + 
                    ": 该注解没有使用 Handle 注解");
        }
        
        ValidatorHandle validatorHandle = getValidatorHandle(handleAnnotation.handle());
        
        // 用来收集错误提示
        StringBuffer errorTip = new StringBuffer();
        
        // 校验
        if (!validatorHandle.handle(annotation, value, errorTip))
        {
            error.put(fieldName, errorTip.toString());
        }
    }
    
    /**
     * 获取ValidatorHandle的实例
     * @param clazz
     * @return
     */
    private static ValidatorHandle getValidatorHandle(Class<? extends ValidatorHandle> clazz)
    {
        Constructor<? extends ValidatorHandle> constructor;
        ValidatorHandle validatorHandle = null;
        try 
        {
            constructor = clazz.getConstructor();
            validatorHandle = constructor.newInstance();
        }
        catch (Exception e)
        {
            LOGGER.error("校验器内部错误:" + clazz.getSimpleName(), e);
            // 对应的校验器发生异常，终止校验
            throw new ValidatorException("实例化:" + clazz.getSimpleName() + " 错误", e);
        }
        
        return validatorHandle;
    }
}
