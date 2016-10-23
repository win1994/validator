package org.fan.validator.core.test.bean;

import org.fan.validator.annotation.ValidatorAnnotation;

public class TestBean {
    
    @ValidatorAnnotation
    private String test;
    
    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
