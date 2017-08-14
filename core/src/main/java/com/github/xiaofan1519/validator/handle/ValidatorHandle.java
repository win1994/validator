package com.github.xiaofan1519.validator.handle;

import com.github.xiaofan1519.validator.bean.FieldMetaData;

/**
 * @author XiaoFan
 *
 */
public interface ValidatorHandle {

	/**
	 * 处理方法
	 * 
	 * @param metaData 字段元数据
	 * @return 是否通过校验
	 */
	boolean handle(FieldMetaData metaData);
}
