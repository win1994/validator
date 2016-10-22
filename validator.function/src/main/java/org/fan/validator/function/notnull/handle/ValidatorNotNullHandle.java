/**
 * 
 */
package org.fan.validator.function.notnull.handle;

import java.lang.reflect.Field;
import java.util.Map;

import org.fan.validator.annotation.ValidatorAnnotation;
import org.fan.validator.handle.ValidatorHandle;

/**
 * 校验字段是否为空
 * @author XiaoFan
 *
 */
public class ValidatorNotNullHandle implements ValidatorHandle {

    @Override
    public void handle(ValidatorAnnotation annotation, Field field, Map<String, String> error) {
        
    }

}
