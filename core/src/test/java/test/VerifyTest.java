package test;

import org.junit.Test;

import com.github.xiaofan1519.validator.core.Validator;
import com.github.xiaofan1519.validator.utils.UnitTestUtil;

import test.bean.TestBean;

public class VerifyTest {

	/**
	 * DefaultValidatorHandle 测试用例
	 */
	@Test
	public void testDefaultHandle() {
		TestBean bean = new TestBean();

		UnitTestUtil.test(() -> {
			Validator.verify(bean);
		}, true);
	}
}
