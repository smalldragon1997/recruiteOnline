package com.job.json;

public class JobJson {
	private String name;
	private String major;
	private String position;
	private int salary;
	private String duty;
	private String address;
	public JobJson(String name, String major, String position, int salary, String duty, String address) {
		super();
		this.name = name;
		this.major = major;
		this.position = position;
		this.salary = salary;
		this.duty = duty;
		this.address = address;
	}
	public JobJson() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
