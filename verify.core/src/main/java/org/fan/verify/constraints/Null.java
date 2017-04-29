/**
 * 
 */
package org.fan.verify.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.fan.verify.annotation.handle.Handle;
import org.fan.verify.handle.impl.NullVerifyHandle;

/**
 * @author Fan
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Handle(NullVerifyHandle.class)
public @interface Null
{
	String name() default "必须为Null";
}
