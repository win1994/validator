/**
 * 
 */
package com.github.xiaofan1519.verify.utils;

import com.github.xiaofan1519.verify.exception.VerifyException;

/**
 * 提供常用的校验方法
 * @author Fan
 *
 */
public abstract class VerifyUtil
{
	
	/**
     * 当 field 为null时，抛出异常
     * 
     * @param field 要校验的字段
     */
    public static void verifyNull(Object field)
    {
    	if (null == field)
    	{
    		throw new VerifyException("参数 field 值为null");
    	}
    }
    
    /**
     * 当 field 为null或者空时，抛出异常
     * 
     * @param field
     */
    public static void verifyEmpty(CharSequence field)
    {
    	verifyNull(field);
    	if (field.length() == 0)
    	{
    		throw new VerifyException("参数 field 值为空");
    	}
    }
    
    /**
     * 当 field 的长度不在指定范围内，抛出异常
     * 
     * @param field 要校验的字段
     * @param min 最小长度
     * @param max 最大长度
     */
    public static void verifyLen(CharSequence field, int min, int max)
    {
    	verifyNull(field);
    	int length = field.length();
    	if (length < min || length > max)
    	{
    		throw new VerifyException("参数 field 值 长度不合法");
    	}
    }
    
    /**
     * 当 field 的长度不在指定范围内，抛出异常
     * 该方法允许字段值为null，适用于非必填字段校验
     * 
     * @param field 要校验的字段
     * @param min 最小长度
     * @param max 最大长度
     */
    public static void verifyLenAllowNull(CharSequence field, int min, int max)
    {
    	if (null == field)
    	{
    		return;
    	}
    	
    	verifyLen(field, min, max);
    }
    
    /**
     * 当 field 不等于任何一个枚举值，抛出异常
     * 
     * @param field 要校验的字段
     * @param enums 枚举值
     */
    public static void verifyEnum(CharSequence field, CharSequence... enums)
    {
    	verifyNull(field);
    	for (CharSequence charSequence : enums) {
			if (field.equals(charSequence))
			{
				return;
			}
		}
    	throw new VerifyException("参数 field 值 枚举校验失败");
    }
    
    /**
     * 当 field 不等于任何一个枚举值，抛出异常
     * 该方法允许字段值为null，适用于非必填字段校验
     * 
     * @param field 要校验的字段
     * @param enums 枚举值
     */
    public static void verifyEnumAllowNull(CharSequence field, CharSequence... enums)
    {
    	if (null == field)
    	{
    		return;
    	}
    	
    	verifyEnum(field, enums);
    }
    
}
