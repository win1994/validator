package test.length.bean;

import org.fan.verify.function.length.annotation.VerifyLen;

/**
 * @author XiaoFan
 *
 */
public class TestBean
{
    @VerifyLen(minLen = 3, maxLen = 10)
    private String name;

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
}
