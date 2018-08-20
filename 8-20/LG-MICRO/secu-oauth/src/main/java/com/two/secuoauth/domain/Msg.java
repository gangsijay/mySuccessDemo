package com.two.secuoauth.domain;
/**
 * 描述
 *
 * @Author: 我爱大金子
 * @Description: 描述
 * @Date: Create in 16:14 2017/7/5
 */
public class Msg {
    private String title;
    private String content;
    private String etraInfo;
    public Msg(String title, String content, String etraInfo) {
        super();
        this.title = title;
        this.content = content;
        this.etraInfo = etraInfo;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getEtraInfo() {
        return etraInfo;
    }
    public void setEtraInfo(String etraInfo) {
        this.etraInfo = etraInfo;
    }
}