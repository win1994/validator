/**
 * 
 */
package org.fan.validator.handle.impl;

import org.fan.validator.exception.impl.NotImplementedException;
import org.fan.validator.handle.ValidatorHandle;

/**
 * 注解默认的handle
 * @author 75445
 *
 */
public class DefaultValidatorHandle implements ValidatorHandle {

    @Override
    public void handle() {
        throw new NotImplementedException();
    }

}
