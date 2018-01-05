package com.job.entity;

public class Man {
	private int manId;
	private String email;
	private String password;
	public Man(int manId, String email, String password) {
		super();
		this.manId = manId;
		this.email = email;
		this.password = password;
	}
	public Man() {
		super();
	}
	public int getManId() {
		return manId;
	}
	public void setManId(int manId) {
		this.manId = manId;
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
