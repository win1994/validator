/**
 * 
 */
package org.fan.validator.exception;

import java.util.Map;

/**
 * 校验器异常
 * @author XiaoFan
 *
 */
public class ValidatorException extends RuntimeException {

    private static final long serialVersionUID = -7521481307777742644L;
    
    private Map<String, String> error;
    
    public ValidatorException()
    {
        super();
    }
    
    public ValidatorException(String message)
    {
        super(message);
    }
    
    public ValidatorException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public ValidatorException(Throwable cause)
    {
        super(cause);
    }
    
    /**
     * 
     * @param error 存放不符合校验规则的字段信息
     *              key = 不符合校验规则的字段名
     *              value = 不符合校验规则的描述信息
     */
    public ValidatorException(Map<String, String> error)
    {
        this.error = error;
    }

    public Map<String, String> getError() {
        return error;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }
}
