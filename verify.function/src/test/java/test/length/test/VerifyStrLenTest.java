package test.length.test;

import org.junit.Test;

import com.github.xiaofan1519.verify.core.Verify;
import com.github.xiaofan1519.verify.utils.UnitTestUtil;

import test.length.bean.TestBean;

/**
 * @author XiaoFan
 *
 */
public class VerifyStrLenTest
{
    @Test
    public void minLen()
    {
        TestBean bean = new TestBean();
        bean.setName("aw");
        
        UnitTestUtil.test(() -> {
			Verify.verify(bean);
		}, true);
        
        bean.setName("awa");
        UnitTestUtil.test(() -> {
			Verify.verify(bean);
		}, false);
    }
    
    @Test
    public void maxLen()
    {
        TestBean bean = new TestBean();
        bean.setName("12345678901");
        
        UnitTestUtil.test(() -> {
			Verify.verify(bean);
		}, true);
        
        bean.setName("1234567890");
        UnitTestUtil.test(() -> {
			Verify.verify(bean);
		}, false);
    }
}
