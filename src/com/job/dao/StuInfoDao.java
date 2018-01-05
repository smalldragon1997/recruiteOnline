package com.job.dao;

import com.job.entity.StuInfo;
//增删改查 学生信息
public interface StuInfoDao {

	 public int insertStuInfo(StuInfo stuInfo);
	 public int deleteStuInfo(int stuId);
	 public int updateStuInfo(StuInfo stuInfo);
	 public StuInfo getStuInfo(int stuId);
}
