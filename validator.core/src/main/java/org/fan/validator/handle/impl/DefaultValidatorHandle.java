package org.fan.validator.handle.impl;

import java.lang.annotation.Annotation;

import org.fan.validator.exception.impl.NotImplementedException;
import org.fan.validator.handle.ValidatorHandle;

/**
 * 注解默认的handle
 * 
 * @author XiaoFan
 *
 */
public class DefaultValidatorHandle implements ValidatorHandle
{

    @Override
    public boolean handle(Annotation annotation, Object value, StringBuffer error)
    {
        throw new NotImplementedException("我没有实现，不要使用我");
    }

}
