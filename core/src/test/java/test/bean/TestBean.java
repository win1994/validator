package test.bean;

import com.github.xiaofan1519.validator.annotation.ValidatorAnnotation;

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
