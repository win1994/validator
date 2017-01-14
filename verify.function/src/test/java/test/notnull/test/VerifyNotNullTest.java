package test.notnull.test;

import org.fan.verify.core.Verify;
import org.fan.verify.exception.VerifyException;
import org.fan.verify.utils.UnitTestUtil;
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
        
        UnitTestUtil.test(()-> {
            Verify.verify(bean);
        }, (e) -> {
            VerifyException ex = null;
            if (!(e instanceof VerifyException))
            {
                return false;
            }
            
            ex = (VerifyException)e;
            if (ex.getError().containsKey("name"))
            {
                return true;
            }
            
            return false;
        });
    }
}
