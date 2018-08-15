package com.zhm.db;

import java.io.Serializable;

/**
 * Created by zhm on 17-2-25.
 */
public class AppUser implements Serializable{
    private static final long serialVersionUID = -2081050471980978153L;
    private Integer id;
    private String client_id;
    private String userid;
    private int isadmin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(int isadmin) {
        this.isadmin = isadmin;
    }
}
