package org.fan.verify.function.notnull.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.fan.verify.annotation.handle.Handle;
import org.fan.verify.function.notnull.handle.VerifyNullHandle;

/**
 * 校验空指针注解
 * @author XiaoFan
 *
 */
@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Handle(handle = VerifyNullHandle.class)
public @interface VerifyNull
{

}
