package org.fan.verify.exception.impl;

import org.fan.verify.exception.VerifyException;

/**
 * 当注解没有填写对应的handle时，会抛出该异常
 * 
 * @author XiaoFan
 *
 */
public class NotImplementedException extends VerifyException
{

    private static final long serialVersionUID = 6759848126156016546L;

    public NotImplementedException()
    {
        super();
    }

    public NotImplementedException(String message)
    {
        super(message);
    }

    public NotImplementedException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NotImplementedException(Throwable cause)
    {
        super(cause);
    }
}
