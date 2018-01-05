// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 18:57:58
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RegisterJson.java

package com.job.json;


public class RegisterJson
{
    private String registerEmail;
    private String registerPassword;
    private String registerPasswordAgain;
    private String asManager[];
	public RegisterJson(String registerEmail, String registerPassword, String registerPasswordAgain,
			String[] asManager) {
		super();
		this.registerEmail = registerEmail;
		this.registerPassword = registerPassword;
		this.registerPasswordAgain = registerPasswordAgain;
		this.asManager = asManager;
	}
	public RegisterJson() {
		super();
	}
	public String getRegisterEmail() {
		return registerEmail;
	}
	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}
	public String getRegisterPassword() {
		return registerPassword;
	}
	public void setRegisterPassword(String registerPassword) {
		this.registerPassword = registerPassword;
	}
	public String getRegisterPasswordAgain() {
		return registerPasswordAgain;
	}
	public void setRegisterPasswordAgain(String registerPasswordAgain) {
		this.registerPasswordAgain = registerPasswordAgain;
	}
	public String[] getAsManager() {
		return asManager;
	}
	public void setAsManager(String[] asManager) {
		this.asManager = asManager;
	}
    
    
}
