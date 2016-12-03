package org.fan.validator.function.length.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.fan.validator.annotation.Handle;
import org.fan.validator.function.length.handle.ValidatorStrLenHandle;

/**
 * 校验字符串长度
 * @author XiaoFan
 *
 */
@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Handle(handle = ValidatorStrLenHandle.class)
public @interface ValidatorStrLen
{
    /**
     * 最小长度
     * @return
     */
    public int minLen() default 1;
    
    /**
     * 最大长度 
     * 当该值为 -1 时，不进行校验
     * @return
     */
    public int maxLen() default -1;
}
