package com.job.entity;

import java.sql.Timestamp;

public class StuLog {
	private int stuId;
	private Timestamp logTime;
	private Timestamp ableTime;
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
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
	public StuLog() {
		super();
	}
	public StuLog(int stuId, Timestamp logTime, Timestamp ableTime) {
		super();
		this.stuId = stuId;
		this.logTime = logTime;
		this.ableTime = ableTime;
	}
}
