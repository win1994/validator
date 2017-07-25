package com.github.xiaofan1519.validator.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.xiaofan1519.validator.annotation.handle.Handle;
import com.github.xiaofan1519.validator.exception.ValidatorException;
import com.github.xiaofan1519.validator.handle.ValidatorHandle;

/**
 * @author XiaoFan
 *
 */
public abstract class Validator {
	private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);

	/**
	 * 校验方法
	 * 
	 * @param bean
	 *            要被校验的类
	 */
	public static final void verify(Object bean) {
		Map<String, String> error = new LinkedHashMap<>();
		verify(bean, error);

		// error 不为空
		if (error.size() != 0) {
			throw new ValidatorException(null, error);
		}
	}

	/**
	 * 内部的校验方法
	 * 
	 * @param bean
	 *            要被校验的类
	 * @param error
	 *            字段不匹配时的错误提示
	 */
	private static void verify(Object bean, Map<String, String> error) {
		if (null == bean) {
			return;
		}

		Class<?> clazz = bean.getClass();

		// 获取所有的字段
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Object value = null;
			try {
				Method method = getPublicMethodByField(bean, field.getName());
				if (null == method) {
					continue;
				}
				value = getFieldValue(bean, method);
			} catch (ValidatorException e) {
				continue;
			}

			// 获取字段上的注解
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				verifyHandle(annotation, field.getName(), value, error);
			}

			// 字段内容为null
			if (value == bean) {
				continue;
			}

			// TODO 这里会造成 stackoverflow，暂时不校验类中类
			/*
			 * // 根据类型选择对应的校验方式 if (value instanceof Map<?, ?>) {
			 * verifyMap((Map<?, ?>) value, error); } else if (value instanceof
			 * Collection<?>) { validateCollection((Collection<?>) value,
			 * error); } else { verify(value, error); }
			 */
		}
	}

	/**
	 * 校验集合中的字段
	 * 
	 * @param map
	 * @param error
	 */
	/*
	 * private static <K, V> void verifyMap(Map<K, V> map, Map<String, String>
	 * error) { Set<K> keys = map.keySet(); Iterator<K> iter = keys.iterator();
	 * while (iter.hasNext()) {
	 * 
	 * Object key = iter.next(); verify(key, error);
	 * 
	 * Object value = map.get(key); verify(value, error); } }
	 */

	/**
	 * 校验集合中的字段
	 * 
	 * @param collection
	 * @param error
	 */
	/*
	 * private static <T> void validateCollection(Collection<T> collection,
	 * Map<String, String> error) { Iterator<?> iter = collection.iterator();
	 * while (iter.hasNext()) { Object value = iter.next(); verify(value,
	 * error); } }
	 */

	/**
	 * 根据字段名称获取它的Get公共方法
	 * 
	 * @param bean
	 * @param fieldName
	 * @return
	 */
	private static Method getPublicMethodByField(Object bean, String fieldName) {
		Method method = null;
		Class<?> clazz = bean.getClass();

		// 获取首字母
		char initial = fieldName.charAt(0);
		StringBuffer methodName = new StringBuffer();
		methodName.append("get");

		// 如果是字母且是小写，则转换为大写
		if (initial >= 97 && initial <= 122) {
			initial -= 32;
			methodName.append(initial + fieldName.substring(1));
		} else {
			methodName.append(fieldName);
		}

		try {
			method = clazz.getMethod(methodName.toString(), new Class<?>[0]);
		} catch (NoSuchMethodException | SecurityException e) {
			LOGGER.debug("字段{}没有对应的{}公共方法", fieldName, methodName.toString(), e);
			return null;
		}

		// 判断是否是公共方法
		int modifiers = method.getModifiers();
		// 防止自己忘记，不使用方法Modifier.isPublic
		if ((modifiers & Modifier.PUBLIC) != Modifier.PUBLIC) {
			LOGGER.debug("{}不是一个公共方法, modifiers:{}", modifiers);
			return null;
		}

		return method;
	}

	/**
	 * 获取字段中的值
	 * 
	 * @param bean
	 * @param field
	 */
	private static Object getFieldValue(Object bean, Method method) {
		Object value = null;

		try {
			// 要求没有参数的Get方法
			value = method.invoke(bean, new Object[0]);
			return value;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOGGER.debug("调用方法失败, 方法名:{}", method.getName(), e);
			throw new ValidatorException(e);
		}
	}

	/**
	 * 调用对应的handle来进行校验
	 * 
	 * @param annotation
	 * @param fieldName
	 * @param error
	 */
	private static void verifyHandle(Annotation annotation, String fieldName, Object value, Map<String, String> error) {
		// 注解的Class
		Class<? extends Annotation> annotationClass = annotation.annotationType();

		// 获取校验器注解
		Handle handleAnnotation = annotationClass.getDeclaredAnnotation(Handle.class);

		// 如果没有说明不是校验注解
		if (null == handleAnnotation) {
			return;
		}

		// 当前字段校验不通过时的提示信息
		String errorTip = null;

		try {
			Method nameField = annotationClass.getMethod("name");
			errorTip = (String) nameField.invoke(annotation);
		} catch (Exception e) {
			LOGGER.error("校验注解没有默认的 name 字段 {}", annotationClass.getSimpleName(), e);
			throw new ValidatorException("校验注解没有默认的 name 字段", e);
		}

		// 获取注解中的handle
		ValidatorHandle validatorHandle = getValidatorHandle(handleAnnotation.value());

		try {
			// 初始化并校验
			validatorHandle.initialize(annotation);
			if (!validatorHandle.handle(value)) {
				error.put(fieldName, errorTip);
			}
		} catch (Exception e) {
			error.put(fieldName, e.getMessage());
			LOGGER.error("Handle 抛出异常 {}", validatorHandle.getClass().getSimpleName(), e);
			throw new ValidatorException(e, error);
		}
	}

	/**
	 * 获取ValidatorHandle的实例
	 * 
	 * @param clazz
	 * @return
	 */
	private static ValidatorHandle getValidatorHandle(Class<? extends ValidatorHandle> clazz) {
		Constructor<? extends ValidatorHandle> constructor;
		ValidatorHandle validatorHandle = null;

		// TODO 暂时没性能要求，不缓存实例对象
		try {
			constructor = clazz.getConstructor();
			validatorHandle = constructor.newInstance();
		} catch (Exception e) {
			LOGGER.error("校验器内部错误:{}", clazz.getSimpleName(), e);

			throw new ValidatorException("实例化:" + clazz.getSimpleName() + " 错误", e);
		}

		return validatorHandle;
	}

	/**
	 * 静态方法校验
	 */

	/**
	 * 当 field 为null时，抛出异常
	 * 
	 * @param field
	 *            要校验的字段
	 */
	public static void isNull(Object field) {
		isNull(field, null);
	}

	/**
	 * 当 field 为null时，抛出异常
	 * 
	 * @param field
	 *            要校验的字段
	 * @param msg
	 *            自定义错误提示信息
	 */
	public static void isNull(Object field, String msg) {
		if (null != field) {
			return;
		}

		if (null == msg) {
			msg = "不能为null";
		}

		throw new ValidatorException(msg);
	}

	/**
	 * 当 field 为null或者空时，返回true
	 * 
	 * @param field
	 *            被校验的字段
	 */
	private static boolean empty(CharSequence field) {
		if (null == field || field.length() == 0) {
			return true;
		}

		return false;
	}

	/**
	 * 当 field 为null或者空时，抛出异常
	 * 
	 * @param field
	 *            被校验的字段
	 */
	public static void notEmpty(CharSequence field) {
		isNull(field);
		notEmpty(field, null);
	}

	/**
	 * 当 field 为null或者空时，抛出异常
	 * 
	 * @param field
	 *            被校验的字段
	 * @param msg
	 *            自定义错误提示信息
	 */
	public static void notEmpty(CharSequence field, String msg) {
		isNull(field);
		if (field.length() == 0) {
			if (null == msg) {
				throw new ValidatorException("不能为空");
			}
			throw new ValidatorException(msg);
		}
	}

	/**
	 * 当 field 的长度不在指定范围内，抛出异常
	 * 
	 * @param field
	 *            被校验的字段, 允许为空
	 * @param min
	 *            最小长度
	 * @param max
	 *            最大长度
	 * @param msg
	 *            自定义错误提示信息
	 */
	public static void inRange(CharSequence field, int min, int max, String msg) {
		if (empty(field)) {
			return;
		}

		int length = field.length();
		if (length < min || length > max) {
			if (empty(msg)) {
				msg = "长度需要在" + min + "和" + max + "之间";
			}
			throw new ValidatorException(msg);
		}
	}

	/**
	 * 当 field 的长度不在指定范围内，抛出异常
	 * 
	 * @param field
	 *            被校验的字段, 允许为空
	 * @param min
	 *            最小长度
	 * @param max
	 *            最大长度
	 */
	public static void inRange(CharSequence field, int min, int max) {
		inRange(field, min, max, null);
	}

	/**
	 * 当 field 不等于任何一个枚举值，抛出异常
	 * 
	 * @param field
	 *            被校验的字段, 允许为空
	 * @param msg
	 *            自定义错误提示信息
	 * @param enums
	 *            枚举值
	 */
	public static void inEnums(CharSequence field, String msg, CharSequence... enums) {
		if (empty(field)) {
			return;
		}

		for (CharSequence charSequence : enums) {
			if (field.equals(charSequence)) {
				return;
			}
		}
		throw new ValidatorException(msg);
	}

	/**
	 * 校验正则表达式
	 * 
	 * @param field
	 *            要校验的字段
	 * @param regEx
	 *            正则表达式
	 * @param msg
	 *            自定义错误提示信息
	 */
	public static void matchRegEx(CharSequence field, String regEx, String msg) {
		Matcher matcher = null;
		try {
			Pattern pattern = Pattern.compile(regEx);
			matcher = pattern.matcher(field);
		} catch (RuntimeException e) {
			throw new ValidatorException(msg, e);
		}

		if (!matcher.matches()) {
			throw new ValidatorException(msg);
		}
	}

	/**
	 * 校验邮箱格式
	 * 
	 * @param field
	 *            要校验的字段
	 */
	public static void isEmail(CharSequence field) {
		isEmail(field, null);
	}

	/**
	 * 校验邮箱格式
	 * 
	 * @param field
	 *            要校验的字段
	 * @param msg
	 *            自定义错误提示信息
	 */
	public static void isEmail(CharSequence field, String msg) {
		if (empty(field)) {
			return;
		}

		String regEx = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";

		if (empty(msg)) {
			msg = "不是一个合法的电子邮件地址";
		}

		matchRegEx(field, regEx, msg);
	}

	/**
	 * 校验是否是合法的数字（负数，小数）
	 * 
	 * @param field
	 *            被校验的字段, 允许为空
	 */
	public static void isNum(CharSequence field) {
		isNum(field, null);
	}

	/**
	 * 校验是否是合法的数字（负数，小数）
	 * 
	 * @param field
	 *            被校验的字段, 允许为空
	 * @param msg
	 *            自定义错误提示信息
	 */
	public static void isNum(CharSequence field, String msg) {
		if (empty(field)) {
			return;
		}

		try {
			new BigDecimal(field.toString());
		} catch (NumberFormatException e) {
			if (empty(msg)) {
				msg = "非法数字";
			}
			throw new ValidatorException(msg);
		}
	}

	/**
	 * 校验是否是整数
	 * 
	 * @param field
	 *            被校验的字段, 允许为空
	 */
	public static void isDigits(CharSequence field) {
		isDigits(field, null);
	}

	/**
	 * 校验是否是整数
	 * 
	 * @param field
	 *            被校验的字段, 允许为空
	 * @param msg
	 *            自定义错误提示信息
	 */
	public static void isDigits(CharSequence field, String msg) {
		if (empty(field)) {
			return;
		}

		for (int i = 0; i < field.length(); i++) {
			if (!Character.isDigit(field.charAt(i))) {
				if (empty(msg)) {
					msg = "字段值不是一个有效的整数";
				}

				throw new ValidatorException(msg);
			}
		}
	}
}
