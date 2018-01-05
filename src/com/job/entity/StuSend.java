package com.job.entity;

import java.sql.Timestamp;

public class StuSend {
	private int stuId;
	private int jobId;
	private Timestamp sendTime;
	private int state;
	public StuSend(int stuId, int jobId, Timestamp sendTime, int state) {
		super();
		this.stuId = stuId;
		this.jobId = jobId;
		this.sendTime = sendTime;
		this.state = state;
	}
	public StuSend() {
		super();
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
