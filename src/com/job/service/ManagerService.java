package com.job.service;

import com.job.json.MsgJson;

// 管理员服务 管理单位信息、管理已投简学生信息
public interface ManagerService {
	//获取 已发布单位
	public MsgJson<String, Object> getJobs(MsgJson<String, Object> msgFronController);
	//添加 单位信息
	public boolean addJob(MsgJson<String, Object> msgFronController);
	//更新 单位信息
	public boolean alterJob(MsgJson<String, Object> msgFronController);
	//删除 单位信息
	public boolean dropJob(MsgJson<String, Object> msgFronController);
	//设置 已投简学生信息
	public MsgJson<String, Object> alterNewStuSend(MsgJson<String, Object> msgFronController);
	//获取 最新已投简学生信息
	public MsgJson<String, Object> getNewStuSend(MsgJson<String, Object> msgFronController);
	//获取 历史已投简学生信息
	public MsgJson<String, Object> getOldStuSend(MsgJson<String, Object> msgFronController);
	//获取 感兴趣已投简学生信息
	public MsgJson<String, Object> getInterestStuSend(MsgJson<String, Object> msgFronController);
	
}
