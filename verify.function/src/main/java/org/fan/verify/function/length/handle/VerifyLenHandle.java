package org.fan.verify.function.length.handle;

import java.lang.annotation.Annotation;

import org.fan.verify.function.length.annotation.VerifyLen;
import org.fan.verify.handle.VerifyHandle;

/**
 * @author XiaoFan
 *
 */
public class VerifyLenHandle implements VerifyHandle
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
        
        VerifyLen strLenAnnotation = (VerifyLen) annotation;
        int minLen = strLenAnnotation.minLen();
        int maxLen = strLenAnnotation.maxLen();
        
        if (minLen > strValue.length())
        {
            error.append("被校验内容不符合最小长度要求");
            return false;
        }
        
        
        if (maxLen != -1 && maxLen < strValue.length())
        {
            error.append("被校验内容不符合最大长度要求");
            return false;
        }
        
        return true;
    }

}
