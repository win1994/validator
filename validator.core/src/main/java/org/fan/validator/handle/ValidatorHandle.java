/**
 * 
 */
package org.fan.validator.handle;

import java.lang.reflect.Field;
import java.util.Map;

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
     */
    public void handle(ValidatorAnnotation annotation, Field field, Map<String, String> error);
}
