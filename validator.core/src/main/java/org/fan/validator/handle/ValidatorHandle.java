/**
 * 
 */
package org.fan.validator.handle;

import org.fan.validator.annotation.ValidatorAnnotation;

/**
 * @author XiaoFan
 *
 */
public interface ValidatorHandle {
    
    /**
     * 处理方法
     * @param annotation 将被处理的注解
     * @param value 将被处理的值
     * @param error 没有通过校验的错误提示
     * @return 是否通过校验
     */
    public boolean handle(ValidatorAnnotation annotation, Object value, StringBuffer error);
}
