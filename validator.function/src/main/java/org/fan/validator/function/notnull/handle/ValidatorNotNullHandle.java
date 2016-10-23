/**
 * 
 */
package org.fan.validator.function.notnull.handle;

import org.fan.validator.annotation.ValidatorAnnotation;
import org.fan.validator.handle.ValidatorHandle;

/**
 * 校验字段是否为空
 * @author XiaoFan
 *
 */
public class ValidatorNotNullHandle implements ValidatorHandle {

    @Override
    public boolean handle(ValidatorAnnotation annotation, Object value, StringBuffer error) {
        return false;
    }

}
