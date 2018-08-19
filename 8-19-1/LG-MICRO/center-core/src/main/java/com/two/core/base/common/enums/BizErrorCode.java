package com.two.core.base.common.enums;

/**
 * 功能描述:  BIZ前缀+功能模块+行为模块编码
 *
 * @author: 田杰
 * @create: 2018-05-15 11:37
 **/
public enum BizErrorCode {

    BIZ_AUTH_LOGIN_ERROR("BIZ_AUTH_LOGIN_ERROR_000001", "用户权限管理模块认证失败。"),
    BIZ_AUTH_NO_USER("BIZ_AUTH_NO_USER_000002", "用户不存在."),
    BIZ_AUTH_NO_LOGIN("BIZ_AUTH_NO_USER_000003", "用户会话过期,请重新登陆系统."),
    BIZ_PERMISSION_QUERY_MENU_ERROR("BIZ_PERMISSION_MENU_ERROR_000001", "菜单模块查询出错."),
    BIZ_PERMISSION_NO_MENU("BIZ_PERMISSION_MENU_ERROR_000002", "菜单模块不存在."),
    BIZ_PERMISSION_DELETE_MENU_ERROR("BIZ_PERMISSION_MENU_ERROR_000003", "更新菜单或资源出错."),
    BIZ_PERMISSION_QUERY_ELEMENT_ERROR("BIZ_PERMISSION_QUERY_ELEMENT_ERROR_000001", "菜单下元素资源出错."),
    BIZ_PERMISSION_SAVE_MENU_ERROR("BIZ_PERMISSION_SAVE_MENU_ERROR", "新增菜单或资源出错."),
    BIZ_PERMISSION_SAVE_ROLE_ERROR("BIZ_PERMISSION_SAVE_ROLE_ERROR", "新增角色出错."),
    BIZ_PERMISSION_NO_ROLE("BIZ_PERMISSION_SAVE_ROLE_ERROR", "查询角色不存在."),
    BIZ_PERMISSION_QUERY_ROLE_ERROR("BIZ_PERMISSION_QUERY_ROLE_ERROR", "查询角色出错."),
    BIZ_PERMISSION_UPDATE_ROLE_ERROR("BIZ_PERMISSION_MENU_ERROR_000003", "更新角色出错."),
	BIZ_PERMISSION_SAVE_PERMISSION_ROLE_ERROR("BIZ_PERMISSION_SAVE_PERMISSION_ROLE_ERROR","角色授权失败");
    public String code;
    public String describe;
    BizErrorCode(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }

}
