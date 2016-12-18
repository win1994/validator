package test;

import static org.junit.Assert.fail;

import java.util.Map;

import org.fan.verify.core.Verify;
import org.fan.verify.exception.VerifyException;
import org.junit.Test;

import test.bean.TestBean;
import test.bean.TestBean1;

public class ValidatorTest {

    /**
     * DefaultValidatorHandle 测试用例
     */
    @Test
    public void testDefaultHandle() {
        TestBean bean = new TestBean();
        
        try
        {            
            Verify.verify(bean);
        }
        catch (VerifyException e)
        {
            if (catchVerifyException(e))
            {
                return ;
            }
        }
        fail("Error");
    }
    
    @Test
    public void testHandle() {
        TestBean1 bean = new TestBean1();
        
        try
        {            
            Verify.verify(bean);
        }
        catch (VerifyException e)
        {
            if (catchVerifyException(e))
            {
                return ;
            }
        }
        fail("Error");
    }
    
    private boolean catchVerifyException(VerifyException e)
    {
        Map<String, String> map = e.getError();
        if (map.containsValue("我没有实现，不要使用我"))
        {
            return true;
        }
        
        if (map.containsValue("我认识你，但就是不让你通过"))
        {
            return true;
        }
        
        return false;
    }
}
