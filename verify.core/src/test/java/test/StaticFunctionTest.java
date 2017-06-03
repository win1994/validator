/**
 * 
 */
package test;

import org.junit.Test;

import com.github.xiaofan1519.verify.core.Verify;
import com.github.xiaofan1519.verify.utils.UnitTestUtil;

/**
 * 公用方法单元测试
 * @author Fan
 *
 */
public class StaticFunctionTest {

	@Test
	public void verifyNull()
	{
		UnitTestUtil.test(() -> {
			Verify.verifyNull(null);
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNull(1);
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNull("");
		}, false);
	}

	@Test
	public void verifyEmpty()
	{
		UnitTestUtil.test(() -> {
			Verify.verifyEmpty("");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyEmpty("1");
		}, false);
	}
	
	@Test
	public void verifyLength()
	{
		UnitTestUtil.test(() -> {
			Verify.verifyLen("", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLen("123", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLen("1", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLen("12", 1, 2);
		}, false);
	}
	
	@Test
	public void verifyLenAllowEmpty()
	{
		UnitTestUtil.test(() -> {
			Verify.verifyLenAllowEmpty("", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLenAllowEmpty("123", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLenAllowEmpty("1", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLenAllowEmpty("12", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLenAllowEmpty(null, 1, 2);
		}, false);
	}
	
	@Test
	public void verifyEnum()
	{
		UnitTestUtil.test(() -> {
			Verify.verifyEnum("1", "2", "3");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyEnum("2", "2", "3");
		}, false);
	}
	
	@Test
	public void verifyEnumAllowEmpty()
	{
		UnitTestUtil.test(() -> {
			Verify.verifyEnumAllowEmpty("1", "2", "3");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyEnumAllowEmpty("2", "2", "3");
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyEnumAllowEmpty(null, "2", "3");
		}, false);
	}
	
	@Test
	public void verifyEmail()
	{
		UnitTestUtil.test(() -> {
			Verify.verifyEmailAllowEmpty(null);
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyEmailAllowEmpty("");
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyEmail("1");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyEmail("xiaofan.com");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyEmail("@xiaofan.com");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyEmail("xiaofan@.com");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyEmail("xiaofanms@outlook.com");
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyEmail("123@qq.com");
		}, false);
	}
	
	@Test
	public void isNum() {
		
		UnitTestUtil.test(() -> {
			Verify.verifyNum(null);
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNum("");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNum("a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNum("1");
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNum("-1");
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNum("1a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNum("0x1");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNum("0x1E");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNum("-0x1E");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNum("1E1");
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNum("-1E1");
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNum("0.a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNum("1.12");
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNumAllowEmpty(null);
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyNumAllowEmpty("");
		}, false);
	}
	
	@Test
	public void isDigits() {
		UnitTestUtil.test(() -> {
			Verify.verifyDigits(null);
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyDigits("");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyDigits("a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyDigits("1");
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyDigits("-1");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyDigits("1a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyDigits("0x1");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyDigits("0x1E");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyDigits("-0x1E");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyDigits("1E1");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyDigits("-1E1");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyDigits("0.a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyDigits("1.12");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyDigitsAllowEmpty(null);
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyDigitsAllowEmpty("");
		}, false);
	}
}
