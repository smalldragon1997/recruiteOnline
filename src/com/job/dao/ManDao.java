package com.job.dao;

import com.job.entity.Man;

//增删改查 管理员
public interface ManDao {

	 public int insertMan(Man man);
	 public int deleteMan(int manId);
	 public int updateMan(Man man);
	 public Man getMan(int manId);
	 public Man getManByEmail(String email);
}
