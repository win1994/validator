/**
 * 
 */
package org.fan.validator.exception;

/**
 * 校验器异常
 * @author 75445
 *
 */
public class ValidatorException extends RuntimeException {

    private static final long serialVersionUID = -7521481307777742644L;
    
    /**
     * 禁用构造方法，要求抛出时必须明确指定异常原因
     */
    private ValidatorException()
    {
        
    }
}
