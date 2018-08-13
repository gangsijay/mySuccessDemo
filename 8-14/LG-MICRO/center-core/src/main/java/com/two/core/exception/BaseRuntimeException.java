package com.two.core.exception;

/**
 * 自定义运行时异常继承该类
 * @Auther: 田杰
 * @Date: 2018/4/2.
 */
public class BaseRuntimeException extends RuntimeException implements BaseException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7774210851719624748L;
	/**
     * 具体异常码
     */
    protected String code;
    /**
     * 异常信息
     */
    protected String message;


    public BaseRuntimeException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseRuntimeException(String code, String format, Object... args) {
        super(String.format(format, args));
        this.code = code;
        this.message = String.format(format, args);
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return code;
    }
}

