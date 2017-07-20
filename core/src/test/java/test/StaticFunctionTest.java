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
			Validator.inRange("", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.inRange("123", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.inRange("1", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.inRange("12", 1, 2);
		}, false);
	}
	
	@Test
	public void verifyLenAllowEmpty()
	{
		UnitTestUtil.test(() -> {
			Validator.inRangeAllowEmpty("", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.inRangeAllowEmpty("123", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.inRangeAllowEmpty("1", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.inRangeAllowEmpty("12", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.inRangeAllowEmpty(null, 1, 2);
		}, false);
	}
	
	@Test
	public void verifyEnum()
	{
		UnitTestUtil.test(() -> {
			Validator.inEnums("1", "提示信息", "2", "3");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.inEnums("2", "提示信息", "2", "3");
		}, false);
	}
	
	@Test
	public void verifyEnumAllowEmpty()
	{
		UnitTestUtil.test(() -> {
			Validator.inEnumsAllowEmpty("1", "提示信息", "2", "3");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.inEnumsAllowEmpty("2", "提示信息", "2", "3");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.inEnumsAllowEmpty(null, "提示信息", "2", "3");
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
