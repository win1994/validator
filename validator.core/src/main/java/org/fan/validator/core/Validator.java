/**
 * 
 */
package org.fan.validator.core;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author XiaoFan
 *
 */
public abstract class Validator {
    
    /**
     * 校验方法
     * @param bean 要被校验的类
     */
    public static final void validate(Object bean)
    {
        // key = 不符合校验规则的字段名 value = 不符合校验规则的描述信息
        Map<String, String> error = new LinkedHashMap<>();
        validate(bean, error);
        
        // 说明有校验错误
        if (error.size() != 0)
        {
            
        }
    }
    
    /**
     * 内部的校验方法
     * @param bean 要被校验的类
     * @param error 字段不匹配时的错误提示
     */
    private static void validate(Object bean , Map<String, String> error)
    {
        
    }
    
    /**
     * 将 map转换成str
     * @param map
     * @return
     */
    private static String mapToStr(Map<String, String> map)
    {
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
