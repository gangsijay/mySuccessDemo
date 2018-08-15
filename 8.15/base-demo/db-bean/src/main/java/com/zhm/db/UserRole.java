package com.zhm.db;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by haiming.zhuang on 2016/7/12.
 */
public class UserRole  implements Serializable {
    private static final long serialVersionUID = -5855827527479872315L;
    private Integer id;
    private String userid;
    private Integer roleid;
    private String client_id;
    private Timestamp entry_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Timestamp getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Timestamp entry_date) {
        this.entry_date = entry_date;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
}
