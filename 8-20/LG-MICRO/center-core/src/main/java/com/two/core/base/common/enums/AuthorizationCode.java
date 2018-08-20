package com.two.core.base.common.enums;

/**
 * 功能描述: 权限相关编码
 *
 * @author: 田杰
 * @create: 2018-04-25 17:02
 **/
public enum AuthorizationCode {


    AUTH_ANYREQUEST_CONFIG_EXIST("AUTH_CONFIG_EXIST-000001", "AnyRequestConfig配置信息已经存在。");

    public String code;
    public String desc;

    AuthorizationCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
