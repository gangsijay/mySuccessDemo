package com.two.core.exception;
/**
 * 功能描述: 权限相关运行时异常类
 *
 * @author: tian.jie
 * @create: 2018-04-25 16:40
 **/
public class AuthorizeRuntimeException extends BaseRuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AuthorizeRuntimeException(String code, String message) {
        super(code, message);
    }

    public AuthorizeRuntimeException(String code, String format, Object... args) {
        super(code, String.format(format, args));
        this.code = code;
        this.message = String.format(format, args);
    }

    /**
     * 实例化异常
     */
    public static AuthorizeRuntimeException wrap(String code, String message, Object... args) {
        return new AuthorizeRuntimeException(code, message, args);
    }
    public static AuthorizeRuntimeException wrap(String code, String message) {
        return new AuthorizeRuntimeException(code, message);
    }
}
