package com.job.entity;

import java.sql.Timestamp;

public class StuInfo {
	private int stuId;
	private String operation;
	private Timestamp operateTime;
	public StuInfo(int stuId, String operation, Timestamp operateTime) {
		super();
		this.stuId = stuId;
		this.operation = operation;
		this.operateTime = operateTime;
	}
	public StuInfo() {
		super();
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
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
