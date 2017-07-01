package test.bean;

import com.github.xiaofan1519.verify.annotation.VerifyAnnotation;

public class TestBean {
    
    @VerifyAnnotation
    private String test;
    
    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
