package com.project.client.vo;

import java.time.LocalDate;
import java.time.Period;

import com.project.client.entity.Client;

public class SimpleClientVO {
	
	private Long id;

	private String name;

	private String email;

	private LocalDate birthday;

	private Integer age;

	
	public SimpleClientVO() {

	}
	
	public SimpleClientVO(Long id, String name, String email, LocalDate birthday) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthday = birthday;
	}

	public SimpleClientVO(Client client) {
		this.age = client.getAge();
		this.birthday = client.getBirthday();
		this.email = client.getEmail();
		this.id = client.getId();
		this.name = client.getName();

	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		if( this.getBirthday() !=null) {
			return Period.between(this.getBirthday(), LocalDate.now()).getYears();
		}
		
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
