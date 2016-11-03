/**
 * 
 */
package org.fan.validator.function.notnull.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.fan.validator.annotation.Handle;
import org.fan.validator.function.notnull.handle.ValidatorNotNullHandle;

/**
 * 校验空指针注解
 * @author XiaoFan
 *
 */
@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Handle(handle = ValidatorNotNullHandle.class)
public @interface ValidatorNotNull
{

}
