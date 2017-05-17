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
	public void verifyLengthAllowNull()
	{
		UnitTestUtil.test(() -> {
			Verify.verifyLenAllowNull("", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLenAllowNull("123", 1, 2);
		}, true);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLenAllowNull("1", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLenAllowNull("12", 1, 2);
		}, false);
		
		UnitTestUtil.test(() -> {
			Verify.verifyLenAllowNull(null, 1, 2);
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
	
	@Test
	public void verifyEmail()
	{
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
}
