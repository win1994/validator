package test.notnull.bean;

import com.github.xiaofan1519.verify.function.notnull.annotation.VerifyNull;

/**
 * @author XiaoFan
 *
 */
public class TestBean {
    
    @VerifyNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
