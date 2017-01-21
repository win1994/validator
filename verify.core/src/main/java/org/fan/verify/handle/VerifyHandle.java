package org.fan.verify.handle;

import java.lang.annotation.Annotation;

/**
 * @author XiaoFan
 *
 */
public interface VerifyHandle
{
    /**
     * 当校验前会首先执行该方法
     *
     * @param annotation 将被处理的注解
     */
    void initialize(Annotation annotation);

    /**
     * 处理方法
     * 
     * @param annotation 将被处理的注解
     * @param value 将被处理的值
     * @param error 没有通过校验的错误提示
     * @return 是否通过校验
     */
    boolean handle(Object value, StringBuffer error);
}
