package com.job.entity;

public class Stu {
	private int stuId;
	private String email;
	private String password;
	public Stu(int stuId, String email, String password) {
		super();
		this.stuId = stuId;
		this.email = email;
		this.password = password;
	}
	public Stu() {
		super();
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
