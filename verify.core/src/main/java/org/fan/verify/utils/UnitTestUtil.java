/**
 * 
 */
package org.fan.verify.utils;

import static org.junit.Assert.*;

/**
 * 单元测试工具类
 * 简化单元测试代码的编写
 * @author Fan
 *
 */
public class UnitTestUtil 
{
	/**
	 * 调用测试用例 
	 * @param lambda 表达式
	 * @param throwException 是否会抛出异常
	 * @throws Exception
	 */
	public static void test(Runnable lambda, boolean throwException)
	{
		try
		{
			lambda.run();			
		}
		catch (Exception e)
		{
			if (!throwException)
			{
				fail(e.getMessage());
			}
			return;
		}
		
		if (throwException)
		{
			fail("no throwException.");
		}
	}
}
