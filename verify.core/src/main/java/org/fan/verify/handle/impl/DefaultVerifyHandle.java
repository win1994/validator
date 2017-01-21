package org.fan.verify.handle.impl;

import java.lang.annotation.Annotation;

import org.fan.verify.exception.impl.NotImplementedException;
import org.fan.verify.handle.VerifyHandle;

/**
 * 注解默认的handle
 * 
 * @author XiaoFan
 *
 */
public class DefaultVerifyHandle implements VerifyHandle
{
    @Override
    public void initialize(Annotation annotation)
    {

    }

    @Override
    public boolean handle(Object value, StringBuffer error)
    {
        throw new NotImplementedException("我没有实现，不要使用我");
    }

}
