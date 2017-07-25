package com.github.xiaofan1519.validator.handle.impl;

import java.lang.annotation.Annotation;

import com.github.xiaofan1519.validator.exception.impl.NotImplementedException;
import com.github.xiaofan1519.validator.handle.ValidatorHandle;

/**
 * 注解默认的handle
 * 
 * @author XiaoFan
 *
 */
public class DefaultHandle implements ValidatorHandle {
	@Override
	public void initialize(Annotation annotation) {

	}

	@Override
	public boolean handle(Object value) {
		throw new NotImplementedException("我没有实现，不要使用我");
	}

}
