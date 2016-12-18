package test.notnull.test;

import static org.junit.Assert.fail;

import org.fan.verify.core.Verify;
import org.fan.verify.exception.VerifyException;
import org.junit.Test;

import test.notnull.bean.TestBean;

/**
 * @author XiaoFan
 *
 */
public class VerifyNotNullTest {
    
    @Test
    public void testValidatorNotNull()
    {
        TestBean bean = new TestBean();
        
        try
        {
            Verify.verify(bean);
        }
        catch (VerifyException e)
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
