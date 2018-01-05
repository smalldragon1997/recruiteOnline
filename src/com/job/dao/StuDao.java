package com.job.dao;

import com.job.entity.Stu;

// 增删改查 学生用户
public interface StuDao {

	 public int insertStu(Stu stu);
	 public int deleteStu(int stuId);
	 public int updateStu(Stu stu);
	 public Stu getStu(int stuId);
	 public Stu getStuByEmail(String email);
}
