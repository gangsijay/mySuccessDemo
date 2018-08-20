package com.two.core.exception;

import com.two.core.base.common.enums.BizCode;
import com.two.core.base.common.enums.BizErrorCode;

/**
 * 业务异常用于在处理业务逻辑时，进行抛出的异常。
 *
 * @Auther: 田杰
 * @Date: 2018/4/2.
 */
public class BizException extends BaseRuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3365674528191893164L;
	public BizException(String code, String message) {
        super(code, message);
    }

    public BizException(String code, String format, Object... args) {
        super(code, String.format(format, args));
        this.code = code;
        this.message = String.format(format, args);
    }

    /**
     * 实例化异常
     */
    public static BizException wrap(String code, String format, Object... args) {
        return new BizException(code, format, args);
    }
    /**
     * 实例化异常
     */
    public static BizException wrap(String code, String message) {
        return new BizException(code, message);
    }

    public static BizException wrap(BizCode bizCodeEnum) {
        return new BizException(bizCodeEnum.code, bizCodeEnum.describe);
    }
    public static BizException wrap(BizErrorCode bizErrorCodeEunm) {
        return new BizException(bizErrorCodeEunm.code, bizErrorCodeEunm.describe);
    }

}
