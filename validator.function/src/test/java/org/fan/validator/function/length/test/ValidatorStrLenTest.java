package org.fan.validator.function.length.test;

import org.junit.Assert;
import org.fan.validator.core.Validator;
import org.fan.validator.exception.ValidatorException;
import org.fan.validator.function.length.bean.TestBean;
import org.junit.Test;

/**
 * @author XiaoFan
 *
 */
public class ValidatorStrLenTest
{
    @Test
    public void minLen()
    {
        TestBean bean = new TestBean();
        bean.setName("aw");
        try
        {
            Validator.validate(bean);
            Assert.fail("不正确的执行顺序。");
        }
        catch (ValidatorException e)
        {
            System.out.println(e);
        }
        
        bean.setName("awa");
        try
        {
            Validator.validate(bean);
        }
        catch (ValidatorException e)
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
            Validator.validate(bean);
            Assert.fail("不正确的执行顺序。");
        }
        catch (ValidatorException e)
        {
            System.out.println(e);
        }
        
        bean.setName("1234567890");
        try
        {
            Validator.validate(bean);
        }
        catch (ValidatorException e)
        {
            System.out.println(e);
            Assert.fail("不正确的执行顺序。");
        }
    }
}
