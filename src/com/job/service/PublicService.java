package com.job.service;
// 公共服务 查看单位信息、查看学生信息

import com.job.json.MsgJson;

public interface PublicService {
	//获取 单位信息
	public MsgJson<String, Object> getJob(MsgJson<String, Object> msgFromController);
	//获取 学生信息
	public MsgJson<String, Object> getStuInfo(MsgJson<String, Object> msgFromController);

	//获取所有单位
	public MsgJson<String, Object> getAllJobs();
}
