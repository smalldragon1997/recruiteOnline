package com.job.service;

import com.job.json.MsgJson;

// 安全服务 用于登录、注册
public interface SecurityService {
	//登录
	public MsgJson<String, Object> login(MsgJson<String, Object> msgFromController);
	//注册
	public MsgJson<String, Object> register(MsgJson<String, Object> msgFromController);
	//免登录
	public MsgJson<String, Object> freeLogin(MsgJson<String, Object> msgFromController);
	//注销
	public MsgJson<String, Object> exit(MsgJson<String, Object> msgFromController);
	//修改密码
	public void alterPwd(MsgJson<String, Object> msgFromController);
}
