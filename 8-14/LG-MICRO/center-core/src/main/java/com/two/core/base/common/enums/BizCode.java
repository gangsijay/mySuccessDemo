package com.two.core.base.common.enums;

/**
 * 功能描述:  BIZ前缀+功能模块+行为模块编码
 *
 * @author: 田杰
 * @create: 2018-05-15 11:37
 **/
public enum BizCode {
    BIZ_AUTH_LOGIN_MSG("BIZ_AUTH_LOGIN_MSG_000001", "用户权限管理模块认证成功。"),
    BIZ_MENU_SAVE_MSG("BIZ_MENU_SAVE_MSG", "保存菜单成功。"),
    BIZ_MENU_DELETE_MSG("BIZ_MENU_DELETE_MSG", "删除菜单成功。"),
    BIZ_MENU_EDIT_MSG("BIZ_MENU_EDIT_MSG", "修改菜单成功。"),
    BIZ_ELEMENT_SAVE_MSG("BIZ_ELEMENT_SAVE_MSG", "保存元素成功。"),
    BIZ_ELEMENT_DELETE_MSG("BIZ_ELEMENT_DELETE_MSG", "删除菜单成功。"),
    BIZ_ELEMENT_EDIT_MSG("BIZ_ELEMENT_EDIT_MSG", "修改元素成功。"),
    BIZ_PERMISSION_SAVE_ROLE_MSG("BIZ_PERMISSION_SAVE_ROLE_MSG", "新增角色成功."),
    BIZ_PERMISSION_UPDATE_ROLE_MSG("BIZ_PERMISSION_UPDATE_ROLE_MSG", "更新角色成功."),
	BIZ_SAVE_PERMISSION_ROLE_MSG("BIZ_SAVE_PERMISSION_ROLE_MSG","角色授权成功"),
	BIZ_SAVE_USER_ROLE_MSG("BIZ_SAVE_USER_ROLE_MSG","用户角色保存成功");
    public String code;
    public String describe;
    BizCode(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }

}
