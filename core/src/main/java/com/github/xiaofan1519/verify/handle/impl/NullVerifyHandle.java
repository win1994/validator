/**
 * 
 */
package com.github.xiaofan1519.verify.handle.impl;

import java.lang.annotation.Annotation;

import com.github.xiaofan1519.verify.handle.VerifyHandle;

/**
 * 被校验的字段必须为null
 * @author Fan
 *
 */
public class NullVerifyHandle implements VerifyHandle {

	@Override
	public void initialize(Annotation annotation) {
		
	}

	@Override
	public boolean handle(Object value) {
		return null == value;
	}

}
