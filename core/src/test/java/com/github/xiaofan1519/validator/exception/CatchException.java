package com.github.xiaofan1519.validator.exception;

/**
 * 异常处理类，供lambda使用
 * 
 * @author XiaoFan
 *
 */
@FunctionalInterface
public interface CatchException {
	/**
	 * 当发生异常时，交给程序来处理
	 * 
	 * @param throwable
	 *            已捕获的异常
	 * @return 是否已处理该异常
	 */
	boolean handle(Throwable throwable);
}
