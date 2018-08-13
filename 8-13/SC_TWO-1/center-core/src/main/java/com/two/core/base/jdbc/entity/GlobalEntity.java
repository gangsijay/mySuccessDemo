package com.two.core.base.jdbc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class GlobalEntity implements Serializable,ILongIdEntity{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "snowFlakeId")
	@GenericGenerator(name = "snowFlakeId", strategy = "com.two.core.base.jdbc.id.SnowflakeId")
	@Column(name = "id")
	private Long id;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
