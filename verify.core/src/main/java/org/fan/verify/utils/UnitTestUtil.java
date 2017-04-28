/**
 * 
 */
package org.fan.verify.utils;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.fan.verify.exception.CatchException;

/**
 * 单元测试工具类
 * 简化单元测试代码的编写
 * @author Fan
 *
 */
public class UnitTestUtil 
{
	private static final Logger LOGGER = Logger.getLogger(UnitTestUtil.class);
	
    /**
     * 调用测试用例
     * lambda 不会抛出异常，否则失败
     * @param lambda 表达式
     */
    public static void test(Runnable lambda)
    {
        try
        {
            lambda.run();
        }
        catch (Throwable e)
        {
        	LOGGER.debug(e);
            fail(e.getMessage());
        }
    }
    
	/**
	 * 调用测试用例
	 * lambda 可能会抛出异常，是否符合预期结果需要由 throwException 的值来判断
	 * @param lambda 表达式
	 * @param throwException 是否会抛出异常
	 */
	public static void test(Runnable lambda, boolean throwException)
	{
		try
		{
			lambda.run();			
		}
		catch (Throwable e)
		{
			LOGGER.debug(e);
			
		    // 当使用者认为不会抛出异常时
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
	
	/**
     * 调用测试用例
     * lambda 可能会抛出异常，是否符合预期结果需要由 catchException 的返回值来判断
     * @param lambda 表达式
     * @param catchException 对抛出的异常进行处理
     * @see CatchException
     */
    public static void test(Runnable lambda, CatchException catchException)
    {
        try
        {
            lambda.run();           
        }
        catch (Throwable e)
        {
        	LOGGER.debug(e);
        	
            // 当异常没有被处理时
            if (null == catchException || !catchException.handle(e))
            {
                fail(e.getMessage());
            }
            return;
        }
    }
}
