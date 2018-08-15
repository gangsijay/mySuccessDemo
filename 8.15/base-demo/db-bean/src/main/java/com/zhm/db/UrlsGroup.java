package com.zhm.db;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhm on 16-8-6.
 */
public class UrlsGroup implements Serializable{
    private static final long serialVersionUID = -9055748478508754104L;
    private Integer id;
    private String name;
    private List<Urls> urls;
    private String client_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Urls> getUrls() {
        return urls;
    }

    public void setUrls(List<Urls> urls) {
        this.urls = urls;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
}
