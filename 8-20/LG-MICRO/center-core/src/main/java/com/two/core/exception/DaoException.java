package com.two.core.exception;

/**
 * 功能描述: dao层的异常处理类
 *
 * @Auther: 田杰
 * @Date: 2018/4/12 23:21
 */
public class DaoException extends BaseCheckedException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7928087695175344455L;

	public DaoException(Exception e) {
        super(e);
    }

    public DaoException(String code, String message) {
        super(code, message);
    }

    public DaoException(String code, String format, Object... args) {
        super(code, format, args);
    }

    /**
     * 实例化异常
     */
    public static DaoException wrap(Exception e) {
        return new DaoException(e);
    }

    /**
     * 实例化异常
     */
    public static DaoException wrap(String code, String message, Object... args) {
        return new DaoException(code, message, args);
    }

    /**
     * 实例化异常
     */
    public static DaoException wrap(String code, String message) {
        return new DaoException(code, message);
    }
}
