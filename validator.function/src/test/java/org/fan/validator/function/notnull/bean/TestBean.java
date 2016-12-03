package org.fan.validator.function.notnull.bean;

import org.fan.validator.function.notnull.annotation.ValidatorNotNull;

/**
 * @author XiaoFan
 *
 */
public class TestBean {
    
    @ValidatorNotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
