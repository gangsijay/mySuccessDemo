package com.two.lgums.service.bo;

import java.util.List;

public class MenuTreeBO {
	private long id;
	private String text;
	private String type;
	private String code;
	private Long parentId;
	private String plat;
	private String state;
	private List<MenuTreeBO> children;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getPlat() {
		return plat;
	}
	public void setPlat(String plat) {
		this.plat = plat;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<MenuTreeBO> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTreeBO> children) {
		this.children = children;
	}
}
