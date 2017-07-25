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
	public void isNull()
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
	public void notEmpty()
	{
		UnitTestUtil.test(() -> {
			Validator.notEmpty("");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.notEmpty("1");
		}, false);
	}
	
	@Test
	public void inRange()
	{
		UnitTestUtil.test(() -> {
			Validator.inRange(null, 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.inRange("", 1, 2);
		}, false);
		
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
	public void isEnum()
	{
		UnitTestUtil.test(() -> {
			Validator.inEnums("1", "提示信息", "2", "3");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.inEnums("2", "提示信息", "2", "3");
		}, false);
	}
	
	@Test
	public void isEmail()
	{
		UnitTestUtil.test(() -> {
			Validator.isEmail("1");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isEmail("xiaofan.com");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isEmail("@xiaofan.com");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isEmail("xiaofan@.com");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isEmail("xiaofanms@outlook.com");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.isEmail("123@qq.com");
		}, false);
	}
	
	@Test
	public void isNum() {
		
		UnitTestUtil.test(() -> {
			Validator.isNum(null);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.isNum("");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.isNum("a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isNum("1");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.isNum("-1");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.isNum("1a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isNum("0x1");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isNum("0x1E");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isNum("-0x1E");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isNum("1E1");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.isNum("-1E1");
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.isNum("0.a");
		}, true);
		
		UnitTestUtil.test(() -> {
			Validator.isNum("1.12");
		}, false);
	}
	
	@Test
	public void isDigits() {
		UnitTestUtil.test(() -> {
			Validator.isDigits(null);
		}, false);
		
		UnitTestUtil.test(() -> {
			Validator.isDigits("");
		}, false);
		
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
	}
}
