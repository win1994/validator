/**
 * 
 */
package org.fan.validator.function.notnull.handle;

import java.lang.annotation.Annotation;

import org.fan.validator.handle.ValidatorHandle;

/**
 * 校验字段是否为空
 * @author XiaoFan
 *
 */
public class ValidatorNotNullHandle implements ValidatorHandle {

    @Override
    public boolean handle(Annotation annotation, Object value, StringBuffer error) {
        return false;
    }

}
