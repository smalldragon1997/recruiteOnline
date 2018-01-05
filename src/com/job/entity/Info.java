package com.job.entity;
import java.sql.Timestamp;
// 对应info表
public class Info {

	private int stuId;
	private String name;
	private String sex;
	private String major;
	private String birthday;
	private String majorClass;
	private String rewards;
	private String resume;
	private int exceptSal;
	public Info(int stuId, String name, String sex, String major, String birthday, String majorClass, String rewards,
			String resume, int exceptSal) {
		super();
		this.stuId = stuId;
		this.name = name;
		this.sex = sex;
		this.major = major;
		this.birthday = birthday;
		this.majorClass = majorClass;
		this.rewards = rewards;
		this.resume = resume;
		this.exceptSal = exceptSal;
	}
	public Info() {
		super();
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getMajorClass() {
		return majorClass;
	}
	public void setMajorClass(String majorClass) {
		this.majorClass = majorClass;
	}
	public String getRewards() {
		return rewards;
	}
	public void setRewards(String rewards) {
		this.rewards = rewards;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public int getExceptSal() {
		return exceptSal;
	}
	public void setExceptSal(int exceptSal) {
		this.exceptSal = exceptSal;
	}
	
}
