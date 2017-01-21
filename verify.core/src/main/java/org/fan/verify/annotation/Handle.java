package org.fan.verify.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.fan.verify.handle.VerifyHandle;
import org.fan.verify.handle.impl.DefaultVerifyHandle;

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
    Class<? extends VerifyHandle> handle() default DefaultVerifyHandle.class;
}
