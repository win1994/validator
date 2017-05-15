package com.github.xiaofan1519.verify.function.notnull.handle;

import java.lang.annotation.Annotation;

import com.github.xiaofan1519.verify.handle.VerifyHandle;

/**
 * 校验字段是否为空
 * @author XiaoFan
 *
 */
public class VerifyNullHandle implements VerifyHandle
{
    @Override
    public void initialize(Annotation annotation)
    {

    }

    @Override
    public boolean handle(Object value)
    {
        if (null == value)
        {
            return false;
        }

        return true;
    }

}
