package test.handle;

import java.lang.annotation.Annotation;

import org.fan.verify.handle.VerifyHandle;

import test.annotation.TestAnnotation;

/**
 * @author XiaoFan
 *
 */
public class TestVerifyHandle implements VerifyHandle {

    private Annotation annotation;

    @Override
    public void initialize(Annotation annotation)
    {
        this.annotation = annotation;
    }

    @Override
    public boolean handle(Object value, StringBuffer error) {
        
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
