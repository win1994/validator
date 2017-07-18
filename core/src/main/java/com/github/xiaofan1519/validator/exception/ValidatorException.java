package com.github.xiaofan1519.validator.exception;

import java.util.Map;
import java.util.Map.Entry;

/**
 * 校验器异常
 * 
 * @author XiaoFan
 *
 */
public class ValidatorException extends RuntimeException
{

    private static final long   serialVersionUID = -7521481307777742644L;

    /**
     * 错误集合
     */
    private Map<String, String> error;

    /**
     * 字段值
     */
    private String field;
    
    public ValidatorException()
    {
        super();
    }

    public ValidatorException(String message)
    {
        super(message);
    }

    public ValidatorException(String field, String message) {
    	super(message);
    	this.field = field;
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
     * @param cause 被捕获的异常
     * @param error 存放不符合校验规则的字段信息 key = 不符合校验规则的字段名 value = 不符合校验规则的描述信息
     */
    public ValidatorException(Throwable cause, Map<String, String> error)
    {
        super(cause);
        this.error = error;
    }
    
    /**
     * 
     * @param error 存放不符合校验规则的字段信息 key = 不符合校验规则的字段名 value = 不符合校验规则的描述信息
     */
    public ValidatorException(Map<String, String> error)
    {
        this(null, error);
    }

    public Map<String, String> getError()
    {
        return error;
    }

    public void setError(Map<String, String> error)
    {
        this.error = error;
    }

    /**
     * 将Map转换成String
     * 
     * @return 将错误信息以字符串的形式返回
     */
    public String getErrorStr()
    {
        return mapToStr(error);
    }

    @Override
    public String toString()
    {
        StringBuffer buff = new StringBuffer();
        if (null != field) {
        	buff.append(this.getMessage());
            buff.append(":");	
        }
        
        buff.append(this.getMessage());

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
    private String mapToStr(Map<String, String> map)
    {
        StringBuffer buff = new StringBuffer();

        for (Entry<String, String> entry : map.entrySet())
        {
            buff.append("[ ");
            buff.append(entry.getKey() + " : ");
            buff.append(entry.getValue());
            buff.append(" ]");
        }

        return buff.toString();
    }

}
