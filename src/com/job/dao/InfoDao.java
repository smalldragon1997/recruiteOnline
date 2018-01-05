package com.job.dao;

import com.job.entity.Info;

//增删改查 学生信息
public interface InfoDao {
	 public int insertInfo(Info info);
	 public int deleteInfo(int stuId);
	 public int updateInfo(Info info);
	 public Info getInfo(int stuId);
}
