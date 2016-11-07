/**
 * 
 */
package org.fan.validator.function.length.bean;

import org.fan.validator.function.length.annotation.ValidatorStrLen;

/**
 * @author XiaoFan
 *
 */
public class TestBean
{
    @ValidatorStrLen(minLen = 3, maxLen = 10)
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
