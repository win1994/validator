package org.fan.validator.function.notnull.test;

import static org.junit.Assert.fail;

import org.fan.validator.core.Validator;
import org.fan.validator.exception.ValidatorException;
import org.fan.validator.function.notnull.bean.TestBean;
import org.junit.Test;

/**
 * @author XiaoFan
 *
 */
public class ValidatorNotNullTest {
    
    @Test
    public void testValidatorNotNull()
    {
        TestBean bean = new TestBean();
        
        try
        {
            Validator.validate(bean);
        }
        catch (ValidatorException e)
        {
            e.printStackTrace();
            if (e.getError().containsKey("name"))
            {
                return;
            }
        }
        fail("Error");
    }
}
