package test.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.fan.verify.annotation.handle.Handle;

import test.handle.TestVerifyHandle;

@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Handle(handle = TestVerifyHandle.class)
public @interface TestAnnotation
{
    String name() default "测试";
}
