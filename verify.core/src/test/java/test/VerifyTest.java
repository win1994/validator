package test;

import static org.junit.Assert.fail;

import java.util.Map;

import org.fan.verify.core.Verify;
import org.fan.verify.exception.VerifyException;
import org.fan.verify.utils.UnitTestUtil;
import org.junit.Test;

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
