/**
 * 
 */
package org.fan.validator.core.test.handle;

import java.lang.annotation.Annotation;

import org.fan.validator.core.test.annotation.TestAnnotation;
import org.fan.validator.handle.ValidatorHandle;

/**
 * @author XiaoFan
 *
 */
public class TestValidatorHandle implements ValidatorHandle {

    @Override
    public boolean handle(Annotation annotation, Object value, StringBuffer error) {
        
        Class<?> clazz = annotation.annotationType();
        
        if (TestAnnotation.class == clazz)
        {
            TestAnnotation test = (TestAnnotation) annotation;
            System.out.println(test.name());
            error.append("我认识你，但就是不让你通过");
            return false;
        }
        
        return false;
    }

}
