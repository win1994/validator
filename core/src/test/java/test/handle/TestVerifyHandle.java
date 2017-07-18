package test.handle;

import java.lang.annotation.Annotation;

import com.github.xiaofan1519.validator.handle.ValidatorHandle;

import test.annotation.TestAnnotation;

/**
 * @author XiaoFan
 *
 */
public class TestVerifyHandle implements ValidatorHandle {

    private Annotation annotation;

    @Override
    public void initialize(Annotation annotation)
    {
        this.annotation = annotation;
    }

    @Override
    public boolean handle(Object value) {
        
        Class<?> clazz = annotation.annotationType();
        
        if (TestAnnotation.class == clazz)
        {
            TestAnnotation test = (TestAnnotation) annotation;
            System.out.println(test.name());
            return false;
        }
        
        return false;
    }

}
