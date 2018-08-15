package com.zhm.db;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by haiming.zhuang on 2016/7/12.
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 7094004598646216788L;
    private Integer id;//主键
    private String userid;//用户ID
    private String username;//名字
    private String email;//邮箱
    private String mobile;//手机号
    private String invite_mobile;//邀请手机号
    @JsonIgnore
    private String password;//密码
    @JsonFormat(shape=JsonFormat.Shape.STRING,
            pattern="yyyy-MM-dd HH:mm:ss",
            timezone= "GMT+8:00")
    private Date entry_date;//注册时间
    @JsonFormat(shape=JsonFormat.Shape.STRING,
            pattern="yyyy-MM-dd HH:mm:ss",
            timezone= "GMT+8:00")
    private Date last_login;//最后登录时间
    private String avatar;//头像
    private int status;//状态 1：可用  2：不可用
    private String gender;//性别
    private int isadmin;
    private String ID_Num;//身份证号
    private int age;//年龄
    private String area;//地区
    private String driver_type;//驾照类型
    private int has_certificate;//是否有资格证  0:无  1:有

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(int isadmin) {
        this.isadmin = isadmin;
    }

    public String getInvite_mobile() {
        return invite_mobile;
    }

    public void setInvite_mobile(String invite_mobile) {
        this.invite_mobile = invite_mobile;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDriver_type() {
        return driver_type;
    }

    public void setDriver_type(String driver_type) {
        this.driver_type = driver_type;
    }

    public int getHas_certificate() {
        return has_certificate;
    }

    public void setHas_certificate(int has_certificate) {
        this.has_certificate = has_certificate;
    }

    public String getID_Num() {
        return ID_Num;
    }

    public void setID_Num(String ID_Num) {
        this.ID_Num = ID_Num;
    }
}
