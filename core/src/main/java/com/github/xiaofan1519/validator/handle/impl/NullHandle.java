/**
 * 
 */
package com.github.xiaofan1519.validator.handle.impl;

import java.lang.annotation.Annotation;

import com.github.xiaofan1519.validator.handle.ValidatorHandle;

/**
 * 被校验的字段必须为null
 * 
 * @author Fan
 *
 */
public class NullHandle implements ValidatorHandle {

	@Override
	public void initialize(Annotation annotation) {

	}

	@Override
	public boolean handle(Object value) {
		return null == value;
	}

}
