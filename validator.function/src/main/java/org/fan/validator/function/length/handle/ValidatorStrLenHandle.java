/**
 * 
 */
package org.fan.validator.function.length.handle;

import java.lang.annotation.Annotation;

import org.fan.validator.function.length.annotation.ValidatorStrLen;
import org.fan.validator.handle.ValidatorHandle;

/**
 * @author XiaoFan
 *
 */
public class ValidatorStrLenHandle implements ValidatorHandle
{
    
    @Override
    public boolean handle(Annotation annotation, Object value, StringBuffer error)
    {
        if (null == value)
        {
            error.append("被校验内容不能为空");
            return false;
        }
        
        if (value instanceof String == false)
        {
            error.append("被校验内容不是 String 类型");
            return false;
        }
        
        String strValue = (String) value;
        
        ValidatorStrLen strLenAnnotation = (ValidatorStrLen) annotation;
        int minLen = strLenAnnotation.minLen();
        int maxLen = strLenAnnotation.maxLen();
        
        if (minLen > strValue.length())
        {
            error.append("被校验内容不符合最小长度要求");
            return false;
        }
        
        // -1 为不校验
        if (maxLen != -1 && maxLen < strValue.length())
        {
            error.append("被校验内容不符合最大长度要求");
            return false;
        }
        
        return true;
    }

}
