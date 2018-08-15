package com.zhm.db;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by haiming.zhuang on 2016/7/12.
 */
public class Role implements Serializable{
    private static final long serialVersionUID = -7771353569883890210L;
    private Integer id;
    private String name;
    private int checked;
    private String client_id;
    private Timestamp entry_date;

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

    public Timestamp getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Timestamp entry_date) {
        this.entry_date = entry_date;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
}
