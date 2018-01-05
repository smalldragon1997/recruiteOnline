package com.job.entity;

import java.sql.Timestamp;

public class ManStu {
	private int manId;
	private int stuId;
	private String operation;
	private Timestamp operateTime;
	public ManStu(int manId, int stuId, String operation, Timestamp operateTime) {
		super();
		this.manId = manId;
		this.stuId = stuId;
		this.operation = operation;
		this.operateTime = operateTime;
	}
	public ManStu() {
		super();
	}
	public int getManId() {
		return manId;
	}
	public void setManId(int manId) {
		this.manId = manId;
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
