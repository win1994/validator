package org.fan.validator.core.test.bean;

import org.fan.validator.core.test.annotation.TestAnnotation;

public class TestBean {
    
    @TestAnnotation()
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
