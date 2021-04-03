package com.tpsbackendsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Executive {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
//	Auto annotation is used so that our id gets generated automatically(primary key is auto generated and auto incremented)
	
	private String name;
	
	private String Department;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}
	
	
}
