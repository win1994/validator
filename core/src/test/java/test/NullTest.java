package test;

import org.junit.Test;

import com.github.xiaofan1519.validator.core.Validator;
import com.github.xiaofan1519.validator.utils.UnitTestUtil;

import test.bean.NullBean;

/**
 * @author Fan
 * 
 *         2017年4月29日
 * 
 */
public class NullTest {
	@Test
	public void notNull() {
		NullBean bean = new NullBean();
		bean.setTest("123");

		UnitTestUtil.test(() -> {
			Validator.verify(bean);
		}, true);

	}
}
