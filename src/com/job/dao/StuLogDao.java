package com.job.dao;

import com.job.entity.StuLog;
//增删改查 学生登录表
public interface StuLogDao {

	 public int insertStuLog(StuLog stuLog);
	 public int deleteStuLog(int stuId);
	 public int updateStuLog(StuLog stuLog);
	 public StuLog getStuLog(int stuId);
}
