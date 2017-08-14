package com.github.xiaofan1519.validator.handle.impl;

import com.github.xiaofan1519.validator.bean.FieldMetaData;
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
	public boolean handle(FieldMetaData metaData) {
		throw new NotImplementedException("我没有实现，不要使用我");
	}

}
