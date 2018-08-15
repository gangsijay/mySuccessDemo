package com.zhm.model.request;

import io.swagger.annotations.ApiParam;

import java.io.Serializable;

/**
 * Created by zhm on 17-3-16.
 */
public class UpdateUserInfo implements Serializable{
    private static final long serialVersionUID = -6400288188762726876L;
    private String userid;
    @ApiParam("姓名")
    private String username;
    @ApiParam("邮箱")
    private String email;
    @ApiParam("头像")
    private String avatar;
    @ApiParam("性别")
    private String gender;
    @ApiParam("年龄")
    private int age;//年龄
    @ApiParam("地区")
    private String area;//地区
    @ApiParam("身份证号")
    private String ID_Num;//身份证号
    @ApiParam("驾照类型")
    private String driver_type;//驾照类型
    @ApiParam("是否有资格证  0:无  1:有")
    private int has_certificate;//是否有资格证  0:无  1:有

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
