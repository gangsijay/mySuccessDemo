package com.two.core.exception;

/**
 * 	基础异常接口类
 * @Auther: 田杰
 * @Date: 2018/4/2.
 */
public interface BaseException {
	/**
	 * 返回异常编码
	 * @return
	 */
	String getCode();
	/**
	 * 返回异常信息
	 * @return
	 */
	String getMessage();


}
