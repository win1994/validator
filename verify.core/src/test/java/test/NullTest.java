package test;

import org.junit.Test;

import com.github.xiaofan1519.verify.core.Verify;
import com.github.xiaofan1519.verify.utils.UnitTestUtil;

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
