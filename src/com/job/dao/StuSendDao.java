package com.job.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.job.entity.StuSend;
// 学生投简表 增删改查
public interface StuSendDao {

	 public int insertStuSend(StuSend stuSend);
	 public int deleteStuSend(@Param("stuId")int stuId,@Param("jobId")int jobId);
	 public int updateStuSend(StuSend stuSend);
	 public List<StuSend> getStuSendByStuId(int stuId);
	 public StuSend getStuSendById(@Param("stuId")int stuId,@Param("jobId")int jobId);
	 public List<StuSend> getStuSendByJobId(int jobId);
}
