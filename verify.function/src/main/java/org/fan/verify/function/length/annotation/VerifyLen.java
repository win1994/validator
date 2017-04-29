package org.fan.verify.function.length.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.fan.verify.annotation.handle.Handle;
import org.fan.verify.function.length.handle.VerifyLenHandle;

/**
 * 校验字符串长度
 * @author XiaoFan
 *
 */
@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Handle(VerifyLenHandle.class)
public @interface VerifyLen
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
