package org.fan.validator.core.test.annotation;

import org.fan.validator.annotation.Handle;

@Handle()
public @interface TestAnnotation
{
    public String name() default "测试";
}
