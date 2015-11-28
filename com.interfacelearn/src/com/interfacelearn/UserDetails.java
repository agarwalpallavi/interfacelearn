package com.interfacelearn;

import java.io.Serializable;

public class UserDetails implements Serializable{
	public String name;
	public String email;
	public String age;
	public String gender;
	public String ethnicity;
	public double id;
	public boolean selection;
	public int score1;
	public int score2;
	public int score3;
	public UserDetails(String name, String email, String age, String gender, String ethnicity, double id,
			boolean selection, int score1, int score2, int score3) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.ethnicity = ethnicity;
		this.id = id;
		this.selection = selection;
		this.score1 = score1;
		this.score2 = score2;
		this.score3 = score3;
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
	public double getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isSelection() {
		return selection;
	}
	public void setSelection(boolean selection) {
		this.selection = selection;
	}
	public int getScore1() {
		return score1;
	}
	public void setScore1(int score1) {
		this.score1 = score1;
	}
	public int getScore2() {
		return score2;
	}
	public void setScore2(int score2) {
		this.score2 = score2;
	}
	public int getScore3() {
		return score3;
	}
	public void setScore3(int score3) {
		this.score3 = score3;
	}
		
}
