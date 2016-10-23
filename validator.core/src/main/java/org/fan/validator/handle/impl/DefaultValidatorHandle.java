/**
 * 
 */
package org.fan.validator.handle.impl;

import org.fan.validator.annotation.ValidatorAnnotation;
import org.fan.validator.exception.impl.NotImplementedException;
import org.fan.validator.handle.ValidatorHandle;

/**
 * 注解默认的handle
 * @author XiaoFan
 *
 */
public class DefaultValidatorHandle implements ValidatorHandle {

    @Override
    public boolean handle(ValidatorAnnotation annotation, Object value, StringBuffer error) {
        throw new NotImplementedException();
    }

}
