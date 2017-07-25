package com.github.xiaofan1519.validator.handle;

import java.lang.annotation.Annotation;

/**
 * @author XiaoFan
 *
 */
public interface ValidatorHandle {
	/**
	 * 当校验前会首先执行该方法
	 *
	 * @param annotation
	 *            将被处理的注解
	 */
	void initialize(Annotation annotation);

	/**
	 * 处理方法
	 * 
	 * @param value
	 *            将被处理的值
	 * @return 是否通过校验
	 */
	boolean handle(Object value);
}
