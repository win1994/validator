/**
 * 
 */
package test;

import org.junit.Test;

import com.github.xiaofan1519.validator.core.Validator;
import com.github.xiaofan1519.validator.utils.UnitTestUtil;

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
			Validator.isNull(null);
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isNull(1);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.isNull("");
		}, false);
	}

	@Test
	public void verifyEmpty()
	{
		UnitTestUtil.test(() -> {
			Validator.isEmpty("");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isEmpty("1");
		}, false);
	}
	
	@Test
	public void verifyLength()
	{
		UnitTestUtil.test(() -> {
			Validator.verifyLen("", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyLen("123", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyLen("1", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.verifyLen("12", 1, 2);
		}, false);
	}
	
	@Test
	public void verifyLenAllowEmpty()
	{
		UnitTestUtil.test(() -> {
			Validator.verifyLenAllowEmpty("", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.verifyLenAllowEmpty("123", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyLenAllowEmpty("1", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.verifyLenAllowEmpty("12", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.verifyLenAllowEmpty(null, 1, 2);
		}, false);
	}
	
	@Test
	public void verifyEnum()
	{
		UnitTestUtil.test(() -> {
			Validator.verifyEnum("1", "2", "3");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyEnum("2", "2", "3");
		}, false);
	}
	
	@Test
	public void verifyEnumAllowEmpty()
	{
		UnitTestUtil.test(() -> {
			Validator.verifyEnumAllowEmpty("1", "2", "3");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyEnumAllowEmpty("2", "2", "3");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.verifyEnumAllowEmpty(null, "2", "3");
		}, false);
	}
	
	@Test
	public void verifyEmail()
	{
		UnitTestUtil.test(() -> {
			Validator.verifyEmailAllowEmpty(null);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.verifyEmailAllowEmpty("");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.verifyEmail("1");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyEmail("xiaofan.com");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyEmail("@xiaofan.com");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyEmail("xiaofan@.com");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyEmail("xiaofanms@outlook.com");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.verifyEmail("123@qq.com");
		}, false);
	}
	
	@Test
	public void isNum() {
		
		UnitTestUtil.test(() -> {
			Validator.verifyNum(null);
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyNum("");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyNum("a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyNum("1");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.verifyNum("-1");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.verifyNum("1a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyNum("0x1");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyNum("0x1E");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyNum("-0x1E");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyNum("1E1");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.verifyNum("-1E1");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.verifyNum("0.a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.verifyNum("1.12");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.verifyNumAllowEmpty(null);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.verifyNumAllowEmpty("");
		}, false);
	}
	
	@Test
	public void isDigits() {
		UnitTestUtil.test(() -> {
			Validator.isDigits(null);
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isDigits("");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isDigits("a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isDigits("1");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.isDigits("-1");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isDigits("1a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isDigits("0x1");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isDigits("0x1E");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isDigits("-0x1E");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isDigits("1E1");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isDigits("-1E1");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isDigits("0.a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isDigits("1.12");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isDigitsAllowEmpty(null);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.isDigitsAllowEmpty("");
		}, false);
	}
}
