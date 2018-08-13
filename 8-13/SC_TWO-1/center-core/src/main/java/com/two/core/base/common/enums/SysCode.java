package com.two.core.base.common.enums;

/**
 * 系统级别
 * Created by Tjee on 2018/4/2.
 */
public enum SysCode {

    SYS_UNKNOWN_ERROR("SYS-ERROR-000000", "系统内部错误。"),
    SYS_DAO_EXCEPTION("SYS-DAO-000002", "数据库操作出错。");

    public String code;
    public String describe;

    SysCode(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }

}
