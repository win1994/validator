package org.fan.verify.exception;

import java.util.Map;
import java.util.Map.Entry;

/**
 * 校验器异常
 * 
 * @author XiaoFan
 *
 */
public class VerifyException extends RuntimeException
{

    private static final long   serialVersionUID = -7521481307777742644L;

    private Map<String, String> error;

    public VerifyException()
    {
        super();
    }

    public VerifyException(String message)
    {
        super(message);
    }

    public VerifyException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public VerifyException(Throwable cause)
    {
        super(cause);
    }

    /**
     * 
     * @param error 存放不符合校验规则的字段信息 key = 不符合校验规则的字段名 value = 不符合校验规则的描述信息
     */
    public VerifyException(Throwable cause, Map<String, String> error)
    {
        super(cause);
        this.error = error;
    }
    
    /**
     * 
     * @param error 存放不符合校验规则的字段信息 key = 不符合校验规则的字段名 value = 不符合校验规则的描述信息
     */
    public VerifyException(Map<String, String> error)
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
     * @return
     */
    public String getErrorStr()
    {
        return mapToStr(error);
    }

    @Override
    public String toString()
    {
        StringBuffer buff = new StringBuffer();
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
