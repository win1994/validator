/**
 * 
 */
package org.fan.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.fan.validator.handle.impl.DefaultValidatorHandle;

/**
 * 注解样例
 * 
 * @author XiaoFan
 *
 */
@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Handle(handle = DefaultValidatorHandle.class)
public @interface ValidatorAnnotation
{

}
