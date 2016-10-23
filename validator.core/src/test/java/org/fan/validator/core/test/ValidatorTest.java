package org.fan.validator.core.test;

import static org.junit.Assert.*;

import org.fan.validator.core.Validator;
import org.fan.validator.core.test.bean.TestBean;
import org.fan.validator.exception.ValidatorException;
import org.junit.Test;

public class ValidatorTest {

    @Test
    public void testValidate() {
        // fail("Not yet implemented");
        TestBean bean = new TestBean();
        
        try
        {            
            Validator.validate(bean);
        }
        catch (ValidatorException e)
        {
            e.getError();
        }
    }

}
