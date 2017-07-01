package com.github.xiaofan1519.verify.handle.impl;

import java.lang.annotation.Annotation;

import com.github.xiaofan1519.verify.exception.impl.NotImplementedException;
import com.github.xiaofan1519.verify.handle.VerifyHandle;

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
    public boolean handle(Object value)
    {
        throw new NotImplementedException("我没有实现，不要使用我");
    }

}
