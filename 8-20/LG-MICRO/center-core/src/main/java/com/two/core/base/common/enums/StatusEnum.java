package com.two.core.base.common.enums;

/**
 * 功能描述: ${description}
 *
 * @author: 田杰
 * @create: 2018-05-09 17:09
 **/
public enum StatusEnum {
    Y("启用"),
    N("锁定");
    public String value;

    StatusEnum(String value) {
        this.value = value;
    }
}
