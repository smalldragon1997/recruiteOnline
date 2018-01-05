package com.job.dao;

import com.job.entity.ManLog;
//管理员登录 增删改查
public interface ManLogDao {
	
	 public int insertManLog(ManLog manLog);
	 public int deleteManLog(int manId);
	 public int updateManLog(ManLog manLog);
	 public ManLog getManLog(int manId);
}
