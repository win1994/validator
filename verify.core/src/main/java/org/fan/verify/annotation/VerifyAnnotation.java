package org.fan.verify.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.fan.verify.annotation.handle.Handle;
import org.fan.verify.handle.impl.DefaultVerifyHandle;

/**
 * 注解样例
 * 
 * @author XiaoFan
 *
 */
@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Handle(DefaultVerifyHandle.class)
public @interface VerifyAnnotation
{
	String name() default "没有指定Handle";
}
