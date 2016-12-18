package test.length.test;

import org.fan.verify.core.Verify;
import org.fan.verify.exception.VerifyException;
import org.junit.Assert;
import org.junit.Test;

import test.length.bean.TestBean;

/**
 * @author XiaoFan
 *
 */
public class VerifyStrLenTest
{
    @Test
    public void minLen()
    {
        TestBean bean = new TestBean();
        bean.setName("aw");
        try
        {
            Verify.verify(bean);
            Assert.fail("不正确的执行顺序。");
        }
        catch (VerifyException e)
        {
            System.out.println(e);
        }
        
        bean.setName("awa");
        try
        {
            Verify.verify(bean);
        }
        catch (VerifyException e)
        {
            System.out.println(e);
            Assert.fail("不正确的执行顺序。");
        }
    }
    
    @Test
    public void maxLen()
    {
        TestBean bean = new TestBean();
        bean.setName("12345678901");
        
        try
        {
            Verify.verify(bean);
            Assert.fail("不正确的执行顺序。");
        }
        catch (VerifyException e)
        {
            System.out.println(e);
        }
        
        bean.setName("1234567890");
        try
        {
            Verify.verify(bean);
        }
        catch (VerifyException e)
        {
            System.out.println(e);
            Assert.fail("不正确的执行顺序。");
        }
    }
}
