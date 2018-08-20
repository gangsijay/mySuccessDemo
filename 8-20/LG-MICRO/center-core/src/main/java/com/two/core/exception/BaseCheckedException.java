package com.two.core.exception;

/**
 * 非运行期异常基类
 * Created by Tjee on 2018/4/2.
 *
 * @Auther: 田杰
 * @Date: 2018/4/2.
 */
public abstract class BaseCheckedException extends Exception implements BaseException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1634890584849848L;

	/**
     * 异常信息
     */
    protected String message;

    /**
     * 具体异常码
     */
    protected String code;

    public BaseCheckedException(Exception exception) {
        super(exception);
    }

    public BaseCheckedException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseCheckedException(String code, String format, Object... args) {
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
