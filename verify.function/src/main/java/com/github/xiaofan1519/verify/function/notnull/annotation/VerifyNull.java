package com.github.xiaofan1519.verify.function.notnull.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.xiaofan1519.verify.annotation.handle.Handle;
import com.github.xiaofan1519.verify.function.notnull.handle.VerifyNullHandle;

/**
 * 校验空指针注解
 * @author XiaoFan
 *
 */
@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Handle(VerifyNullHandle.class)
public @interface VerifyNull
{

}
