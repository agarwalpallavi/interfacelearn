package com.interfacelearn;

import java.io.Serializable;

public class UserDetails implements Serializable{
	public String name;
	public String email;
	public String age;
	public String gender;
	public String ethnicity;
	public int id;
	
	public UserDetails(String name, String email, String age, String gender, String ethnicity, int id) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.ethnicity = ethnicity;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
