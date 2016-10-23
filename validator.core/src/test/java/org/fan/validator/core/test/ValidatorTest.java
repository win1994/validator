package org.fan.validator.core.test;

import static org.junit.Assert.fail;

import java.util.Map;

import org.fan.validator.core.Validator;
import org.fan.validator.core.test.bean.TestBean;
import org.fan.validator.core.test.bean.TestBean1;
import org.fan.validator.exception.ValidatorException;
import org.junit.Test;

public class ValidatorTest {

    /**
     * DefaultValidatorHandle 测试用例
     */
    @Test
    public void testDefaultValidatorHandle() {
        TestBean bean = new TestBean();
        
        try
        {            
            Validator.validate(bean);
        }
        catch (ValidatorException e)
        {
            if (catchValidatorException(e))
            {
                return ;
            }
        }
        fail("Error");
    }
    
    @Test
    public void testTestValidatorHandle() {
        TestBean1 bean = new TestBean1();
        
        try
        {            
            Validator.validate(bean);
        }
        catch (ValidatorException e)
        {
            if (catchValidatorException(e))
            {
                return ;
            }
        }
        fail("Error");
    }
    
    private boolean catchValidatorException(ValidatorException e)
    {
        Map<String, String> map = e.getError();
        if ("我没有实现，不要使用我".equals(e.getMessage()))
        {
            return true;
        }
        
        if (map.containsValue("我认识你，但就是不让你通过"))
        {
            return true;
        }
        
        return false;
    }
}
