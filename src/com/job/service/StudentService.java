package com.job.service;

import com.job.json.MsgJson;

// 学生服务 管理学生信息、投简
public interface StudentService {
	//添加 学生信息
	public MsgJson<String, Object> addStuInfo(MsgJson<String, Object> msgFromController);
	//更新 学生信息
	public MsgJson<String, Object> alterStuInfo(MsgJson<String, Object> msgFromController);
	//删除 学生信息
	public MsgJson<String, Object> dropStuInfo(MsgJson<String, Object> msgFromController);
	//添加 投简信息
	public MsgJson<String, Object> addStuSend(MsgJson<String, Object> msgFromController);
	//删除 投简信息
	public MsgJson<String, Object> dropStuSend(MsgJson<String, Object> msgFromController);
	//获取 未处理的已投简单位
	public MsgJson<String, Object> getNewStuSendJob(MsgJson<String, Object> msgFronController);
	//获取 已处理的已投简单位
	public MsgJson<String, Object> getOldStuSendJob(MsgJson<String, Object> msgFronController);
	//获取 被感兴趣的已投简单位
	public MsgJson<String, Object> getInterestStuSendJob(MsgJson<String, Object> msgFronController);
}
