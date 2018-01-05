package com.job.entity;

import java.sql.Timestamp;

public class ManLog {
	private int manId;
	private Timestamp logTime;
	private Timestamp ableTime;
	public ManLog(int manId, Timestamp logTime, Timestamp ableTime) {
		super();
		this.manId = manId;
		this.logTime = logTime;
		this.ableTime = ableTime;
	}
	public ManLog() {
		super();
	}
	public int getManId() {
		return manId;
	}
	public void setManId(int manId) {
		this.manId = manId;
	}
	public Timestamp getLogTime() {
		return logTime;
	}
	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}
	public Timestamp getAbleTime() {
		return ableTime;
	}
	public void setAbleTime(Timestamp ableTime) {
		this.ableTime = ableTime;
	}
	
}
