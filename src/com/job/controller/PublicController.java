package com.job.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.job.json.MsgJson;
import com.job.service.PublicService;
import com.job.tools.MyMsgJson;

// 用于接收 公共部分的请求 查询学生、查询单位
@Controller
@RequestMapping("public")
public class PublicController {
	@Autowired
	@Qualifier(value = "publicService")
	private PublicService publicService;

	

	// 获得单位详细信息
	@RequestMapping("getDetailJob")
	@ResponseBody
	public MsgJson<String, Object> getDetailJob(HttpServletRequest request) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setRequestData("jobId", request.getParameter("jobId"));
		return publicService.getJob(msgToView);
	}
	// 获得单位详细信息
	@RequestMapping("getJobs")
	@ResponseBody
	public MsgJson<String, Object> getJobs() {
		return publicService.getAllJobs();
	}

}
