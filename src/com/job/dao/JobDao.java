package com.job.dao;

import java.util.List;

import com.job.entity.Job;

//增删改查 单位信息
public interface JobDao {

	 public int insertJob(Job job);
	 public int deleteJob(int jobId);
	 public int updateJob(Job job);
	 public Job getJob(int jobId);
	 public List<Job> getAllJobs();
}
