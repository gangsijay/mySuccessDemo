package com.zhm.db;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by haiming.zhuang on 2016/7/12.
 */
public class Urls  implements Serializable {
    private static final long serialVersionUID = 5482862730675423904L;
    private Integer id;
    private String name;//名字
    private String link_url;//链接
    private String need_permission;//所需权限
    private Timestamp entry_date;
    private int checked;
    private Integer group_id;//组ID
    private String group_name;//组名
    private int is_menu;//是否为菜单 0 否 1 是
    private String client_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public Timestamp getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Timestamp entry_date) {
        this.entry_date = entry_date;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public int getIs_menu() {
        return is_menu;
    }

    public void setIs_menu(int is_menu) {
        this.is_menu = is_menu;
    }

    public String getNeed_permission() {
        return need_permission;
    }

    public void setNeed_permission(String need_permission) {
        this.need_permission = need_permission;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
}
