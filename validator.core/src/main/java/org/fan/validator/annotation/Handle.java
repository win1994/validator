package org.fan.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.fan.validator.handle.ValidatorHandle;
import org.fan.validator.handle.impl.DefaultValidatorHandle;

/**
 * 注解的注解，指定对应注解的handle
 * 
 * @author XiaoFan
 *
 */
@Target(value = { ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Handle
{

    /**
     * 指定注解使用该handle处理，必须实现 ValidatorHandle 接口
     * 
     * @return
     */
    public Class<? extends ValidatorHandle> handle() default DefaultValidatorHandle.class;
}
