package com.job.entity;

import java.sql.Timestamp;

public class ManReg {
	private int manId;
	private Timestamp regTime;
	private String regIp;
	public ManReg(int manId, Timestamp regTime, String regIp) {
		super();
		this.manId = manId;
		this.regTime = regTime;
		this.regIp = regIp;
	}
	public ManReg() {
		super();
	}
	public int getManId() {
		return manId;
	}
	public void setManId(int manId) {
		this.manId = manId;
	}
	public Timestamp getRegTime() {
		return regTime;
	}
	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	
}
