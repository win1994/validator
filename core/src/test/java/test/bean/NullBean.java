package test.bean;

import com.github.xiaofan1519.verify.constraints.Null;

/**
 * @author Fan
 * 
 * 2017年4月29日
 * 
 */
public class NullBean {
	
	@Null
	private String test;

    /**
     * @param test the test to set
     */
    public void setTest(String test)
    {
        this.test = test;
    }
}
