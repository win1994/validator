package org.fan.validator.core.test.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.fan.validator.annotation.Handle;
import org.fan.validator.core.test.handle.TestValidatorHandle;

@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Handle(handle = TestValidatorHandle.class)
public @interface TestAnnotation
{
    public String name() default "测试";
}
