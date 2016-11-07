/**
 * 
 */
package org.fan.validator.exception;

import java.util.Map;

/**
 * 校验器异常
 * 
 * @author XiaoFan
 *
 */
public class ValidatorException extends RuntimeException {

    private static final long   serialVersionUID = -7521481307777742644L;

    private Map<String, String> error;

    public ValidatorException() {
        super();
    }

    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }

    /**
     * 
     * @param error
     *            存放不符合校验规则的字段信息 key = 不符合校验规则的字段名 value = 不符合校验规则的描述信息
     */
    public ValidatorException(Map<String, String> error) {
        this.error = error;
    }

    public Map<String, String> getError() {
        return error;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }

    /**
     * 将Map转换成String
     * 
     * @return
     */
    public String getErrorStr() {
        return mapToStr(error);
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append("Message: " + this.getMessage());
        
        if (null != error)
        {
            buff.append("    ");
            buff.append("Cause: " + getErrorStr());
        }
        
        return buff.toString();
    }
    
    /**
     * 将 map转换成str
     * 
     * @param map
     * @return
     */
    private String mapToStr(Map<String, String> map) {
        StringBuffer buff = new StringBuffer();

        for (String key : map.keySet()) {
            buff.append("[ ");
            buff.append(key + " : ");
            buff.append(map.get(key));
            buff.append(" ]");
        }

        return buff.toString();
    }

}
