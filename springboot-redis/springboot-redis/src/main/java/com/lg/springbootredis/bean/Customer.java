package com.lg.springbootredis.bean;

import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = -1L;

    private String username;
    private Integer age;

    public Customer(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}