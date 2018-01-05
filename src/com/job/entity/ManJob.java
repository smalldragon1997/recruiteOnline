package com.job.entity;

import java.sql.Timestamp;

public class ManJob {
	private int manId;
	private int jobId;
	private String operation;
	private Timestamp operateTime;
	public ManJob(int manId, int jobId, String operation, Timestamp operateTime) {
		super();
		this.manId = manId;
		this.jobId = jobId;
		this.operation = operation;
		this.operateTime = operateTime;
	}
	public ManJob() {
		super();
	}
	public int getManId() {
		return manId;
	}
	public void setManId(int manId) {
		this.manId = manId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Timestamp getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
	}
	
}
