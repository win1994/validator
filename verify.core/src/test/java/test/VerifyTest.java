package test;

import org.junit.Test;

import com.github.xiaofan1519.verify.core.Verify;
import com.github.xiaofan1519.verify.utils.UnitTestUtil;

import test.bean.TestBean;
import test.bean.TestBean1;

public class VerifyTest {

    /**
     * DefaultValidatorHandle 测试用例
     */
    @Test
    public void testDefaultHandle() {
        TestBean bean = new TestBean();
        
        UnitTestUtil.test(() -> {
			Verify.verify(bean);
		}, true);
    }
    
    @Test
    public void testHandle() {
        TestBean1 bean = new TestBean1();
        
        UnitTestUtil.test(() -> {
			Verify.verify(bean);
		}, true);
    }
}
