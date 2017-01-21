package org.fan.verify.function.notnull.handle;

import java.lang.annotation.Annotation;

import org.fan.verify.handle.VerifyHandle;

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
    public boolean handle(Object value, StringBuffer error)
    {
        if (null == value)
        {
            error.append("该字段要求不能为 Null");
            return false;
        }

        return true;
    }

}
