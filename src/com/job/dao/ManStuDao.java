package com.job.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.job.entity.ManStu;
//管理员 管理学生表
public interface ManStuDao {

	 public int insertManStu(ManStu manStu);
	 public int deleteManStu(@Param("manId")int manId,@Param("stuId")int stuId);
	 public int updateManStu(ManStu manStu);
	 public List<ManStu> getManStuByManId(int manId);
}
