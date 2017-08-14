package com.github.xiaofan1519.validator.exception;

/**
 * 校验器异常
 * 
 * @author XiaoFan
 *
 */
public class ValidatorException extends RuntimeException {

	private static final long serialVersionUID = 1;

	public ValidatorException() {
		super();
	}

	public ValidatorException(String message) {
		super(message);
	}
	
	public ValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidatorException(Throwable cause) {
		super(cause);
	}

}
