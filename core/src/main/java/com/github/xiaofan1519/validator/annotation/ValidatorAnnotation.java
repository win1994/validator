package com.github.xiaofan1519.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.xiaofan1519.validator.annotation.handle.Handle;
import com.github.xiaofan1519.validator.handle.impl.DefaultHandle;

/**
 * 注解样例
 * 
 * @author XiaoFan
 *
 */
@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Handle(DefaultHandle.class)
public @interface ValidatorAnnotation
{
	String name() default "没有指定Handle";
}
