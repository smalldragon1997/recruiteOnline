package com.job.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.job.entity.ManJob;

// 增删改查 管理单位
public interface ManJobDao {

	 public int insertManJob(ManJob manJob);
	 public int deleteManJob(@Param("manId")int manId,@Param("jobId")int jobId);
	 public int updateManJob(ManJob manJob);
	 public List<ManJob> getManJobByManId(int manId);
}
