/**
 * 
 */
package com.github.xiaofan1519.validator.handle.impl;

import com.github.xiaofan1519.validator.bean.FieldMetaData;
import com.github.xiaofan1519.validator.handle.ValidatorHandle;

/**
 * 被校验的字段必须为null
 * 
 * @author Fan
 *
 */
public class NullHandle implements ValidatorHandle {
    
	@Override
	public boolean handle(FieldMetaData metaData) {
	    Object value = metaData.getValue();
		return null == value;
	}

}
