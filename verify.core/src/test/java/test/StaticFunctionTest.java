/**
 * 
 */
package test;

import org.fan.verify.core.Verify;
import org.fan.verify.utils.UnitTestUtil;
import org.junit.Test;

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
			Verify.verifyLength("", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLength("123", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLength("1", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLength("12", 1, 2);
		}, false);
	}
	
	@Test
	public void verifyLengthAllowNull()
	{
		UnitTestUtil.test(() -> {
			Verify.verifyLengthAllowNull("", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLengthAllowNull("123", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLengthAllowNull("1", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLengthAllowNull("12", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLengthAllowNull(null, 1, 2);
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
	public void verifyEnumAllowNull()
	{
		UnitTestUtil.test(() -> {
			Verify.verifyEnumAllowNull("1", "2", "3");
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyEnumAllowNull("2", "2", "3");
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyEnumAllowNull(null, "2", "3");
		}, false);
	}
	
}
