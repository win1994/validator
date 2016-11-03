/**
 * 
 */
package org.fan.validator.exception.impl;

import org.fan.validator.exception.ValidatorException;

/**
 * 当注解没有填写对应的handle时，会抛出该异常
 * 
 * @author XiaoFan
 *
 */
public class NotImplementedException extends ValidatorException
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
