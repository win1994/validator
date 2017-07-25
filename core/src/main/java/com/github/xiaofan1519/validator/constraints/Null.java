/**
 * 
 */
package com.github.xiaofan1519.validator.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.github.xiaofan1519.validator.annotation.handle.Handle;
import com.github.xiaofan1519.validator.handle.impl.NullHandle;

/**
 * @author Fan
 *
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Handle(NullHandle.class)
public @interface Null {
	String name() default "必须为Null";
}
