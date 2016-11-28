/**
 * 
 */
package org.fan.validator.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;
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
public abstract class Validator
{

    private static final Logger LOGGER = Logger.getLogger(Validator.class);

    /**
     * 校验方法
     * 
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
            throw new ValidatorException(null, error);
        }
    }

    /**
     * 内部的校验方法
     * 
     * @param bean 要被校验的类
     * @param error 字段不匹配时的错误提示
     */
    private static void validate(Object bean, Map<String, String> error)
    {
        if (null == bean)
        {
            return;
        }

        Class<?> clazz = bean.getClass();
        // 获取该类所有的字段
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

            // 获取该类字段上的注解
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations)
            {
                validateHandle(annotation, field.getName(), value, error);
            }

            // 防止死循环出现
            if (value == bean)
            {
                continue;
            }

            if (value instanceof Map<?, ?>)
            {
                // 暂不处理
            }
            else if (value instanceof Collection<?>)
            {
                validateCollection((Collection<?>) value, error);
            }
            else
            {
                // 字段中的字段判断是否需要校验
                validate(value, error);
            }
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
            validate(value, error);
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
            throw new ValidatorException();
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
            throw new ValidatorException(e);
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
            throw new ValidatorException(e);
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
        // 首字母大写
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
    private static void validateHandle(Annotation annotation, String fieldName, Object value, Map<String, String> error)
    {
        Handle handleAnnotation = annotation.annotationType().getDeclaredAnnotation(Handle.class);

        // 没有写 Handle 注解，或者不是校验注解的情况
        if (null == handleAnnotation)
        {
            return;
        }

        ValidatorHandle validatorHandle = getValidatorHandle(handleAnnotation.handle());

        // 用来收集错误提示
        StringBuffer errorTip = new StringBuffer();

        try
        {
            // 校验
            if (!validatorHandle.handle(annotation, value, errorTip))
            {
                error.put(fieldName, errorTip.toString());
            }
        }
        catch (Exception e)
        {
            error.put(fieldName, e.getMessage());
            LOGGER.error("Handle 抛出异常" + validatorHandle.getClass().getSimpleName(), e);
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
