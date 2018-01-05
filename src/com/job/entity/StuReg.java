package com.job.entity;

import java.sql.Timestamp;

public class StuReg {
	private int stuId;
	private Timestamp regTime;
	private String regIp;
	public StuReg(int stuId, Timestamp regTime, String regIp) {
		super();
		this.stuId = stuId;
		this.regTime = regTime;
		this.regIp = regIp;
	}
	public StuReg() {
		super();
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
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
