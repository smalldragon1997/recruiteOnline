package com.job.dao;

import com.job.entity.StuReg;
//增删改查学生注册表
public interface StuRegDao {

	 public int insertStuReg(StuReg stuReg);
	 public int deleteStuReg(int stuId);
	 public int updateStuReg(StuReg stuReg);
	 public StuReg getStuReg(int stuId);
}
