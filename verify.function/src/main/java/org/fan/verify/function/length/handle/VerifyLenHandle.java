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
    private Annotation annotation;

    @Override
    public void initialize(Annotation annotation)
    {
        this.annotation = annotation;
    }

    @Override
    public boolean handle(Object value)
    {
        if (null == value)
        {
            return false;
        }
        
        if (!(value instanceof String))
        {
            return false;
        }
        
        String strValue = (String) value;
        
        VerifyLen strLenAnnotation = (VerifyLen) annotation;
        int minLen = strLenAnnotation.minLen();
        int maxLen = strLenAnnotation.maxLen();
        
        if (minLen > strValue.length())
        {
            return false;
        }
        
        if (maxLen != -1 && maxLen < strValue.length())
        {
            return false;
        }
        
        return true;
    }

}
