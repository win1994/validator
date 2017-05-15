package test.notnull.test;

import org.junit.Test;

import com.github.xiaofan1519.verify.core.Verify;
import com.github.xiaofan1519.verify.exception.VerifyException;
import com.github.xiaofan1519.verify.utils.UnitTestUtil;

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
