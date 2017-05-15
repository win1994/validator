/**
 * 
 */
package test;

import org.junit.Test;

import com.github.xiaofan1519.verify.utils.UnitTestUtil;
import com.github.xiaofan1519.verify.utils.VerifyUtil;

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
			VerifyUtil.verifyNull(null);
		}, true);
		
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyNull(1);
		}, false);
		
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyNull("");
		}, false);
	}

	@Test
	public void verifyEmpty()
	{
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyEmpty("");
		}, true);
		
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyEmpty("1");
		}, false);
	}
	
	@Test
	public void verifyLength()
	{
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyLength("", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyLength("123", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyLength("1", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyLength("12", 1, 2);
		}, false);
	}
	
	@Test
	public void verifyLengthAllowNull()
	{
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyLengthAllowNull("", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyLengthAllowNull("123", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyLengthAllowNull("1", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyLengthAllowNull("12", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyLengthAllowNull(null, 1, 2);
		}, false);
	}
	
	@Test
	public void verifyEnum()
	{
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyEnum("1", "2", "3");
		}, true);
		
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyEnum("2", "2", "3");
		}, false);
	}
	
	@Test
	public void verifyEnumAllowNull()
	{
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyEnumAllowNull("1", "2", "3");
		}, true);
		
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyEnumAllowNull("2", "2", "3");
		}, false);
		
		UnitTestUtil.test(() -> {
			VerifyUtil.verifyEnumAllowNull(null, "2", "3");
		}, false);
	}
	
}
