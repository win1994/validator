package test;

import org.fan.verify.core.Verify;
import org.fan.verify.utils.UnitTestUtil;
import org.fan.verify.utils.VerifyUtil;
import org.junit.Test;

import test.bean.NullBean;

/**
 * @author Fan
 * 
 *         2017年4月29日
 * 
 */
public class NullTest
{
    @Test
    public void notNull()
    {
        NullBean bean = new NullBean();
        bean.setTest("123");
        
        UnitTestUtil.test(() -> {
            Verify.verify(bean);
        }, true);
        
    }
}
