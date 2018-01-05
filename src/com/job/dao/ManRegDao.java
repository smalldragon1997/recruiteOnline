package com.job.dao;

import com.job.entity.ManReg;
//管理员注册表 增删改查
public interface ManRegDao {

	 public int insertManReg(ManReg manReg);
	 public int deleteManReg(int manId);
	 public int updateManReg(ManReg manReg);
	 public ManReg getManReg(int manId);
}
