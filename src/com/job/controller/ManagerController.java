package com.job.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.job.entity.Job;
import com.job.json.MsgJson;
import com.job.service.ManagerService;
import com.job.service.PublicService;
import com.job.tools.MyMsgJson;

//用于接收 管理员操作的请求 
// 管理单位、管理投简
@Controller
@RequestMapping("manager")
public class ManagerController {
	@Autowired
	@Qualifier(value = "managerService")
	ManagerService managerService;

	@Autowired
	@Qualifier(value = "publicService")
	PublicService publicService;

	// 查看学生信息
	@RequestMapping("getStudentInfo")
	@ResponseBody
	public MsgJson<String, Object> getStudentInfo(HttpServletRequest request) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setRequestData("stuId", request.getParameter("stuId"));
		return publicService.getStuInfo(msgToView);
	}

	// 查看已发布单位
	@RequestMapping("getReleasedJobs")
	@ResponseBody
	public MsgJson<String, Object> getReleasedJobs(HttpSession session) {
		// 传递session中的manId
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setSessionData("manId", session.getAttribute("manId"));
		return managerService.getJobs(msgToView);
	}

	// 发布单位
	@RequestMapping("releaseJob")
	public String releaseJob(HttpServletRequest request, HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		String name = request.getParameter("name");
		String major = request.getParameter("major");
		String position = request.getParameter("position");
		int salary = Integer.parseInt(request.getParameter("salary"));
		String duty = request.getParameter("duty");
		String address = request.getParameter("address");
		Job job = new Job(1, name, major, position, salary, duty, address);
		msgToView.setRequestData("job", job);
		msgToView.setSessionData("manId", session.getAttribute("manId"));
		managerService.addJob(msgToView);
		return "platform";
	}

	// 删除单位
	@RequestMapping("deleteJob")
	@ResponseBody
	public boolean deleteJob(HttpServletRequest request, HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setRequestData("jobId", request.getParameter("jobId"));
		msgToView.setSessionData("manId", session.getAttribute("manId"));
		return managerService.dropJob(msgToView);
	}

	// 修改单位
	@RequestMapping("updateJob")
	public String updateJob(HttpServletRequest request) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		int jobId = Integer.parseInt(request.getParameter("jobId"));
		String name = request.getParameter("name");
		String major = request.getParameter("major");
		String position = request.getParameter("position");
		int salary = Integer.parseInt(request.getParameter("salary"));
		String duty = request.getParameter("duty");
		String address = request.getParameter("address");
		Job job = new Job(jobId, name, major, position, salary, duty, address);
		msgToView.setRequestData("job", job);
		managerService.alterJob(msgToView);
		return "platform";
	}

	// 查看未读已投简
	@RequestMapping("getUnreadResumes")
	@ResponseBody
	public MsgJson<String, Object> getUnreadResumes(HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setSessionData("manId", session.getAttribute("manId"));
		return managerService.getNewStuSend(msgToView);
	}

	// 查看历史已投简
	@RequestMapping("getOldResumes")
	@ResponseBody
	public MsgJson<String, Object> getOldResumes(HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setSessionData("manId", session.getAttribute("manId"));
		return managerService.getOldStuSend(msgToView);
	}

	// 查看感兴趣已投简
	@RequestMapping("getInterestResumes")
	@ResponseBody
	public MsgJson<String, Object> getInterestResumes(HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setSessionData("manId", session.getAttribute("manId"));
		return managerService.getInterestStuSend(msgToView);
	}

	// 设置不感兴趣
	@ResponseBody
	@RequestMapping("readResume")
	public MsgJson<String, Object> readResume(HttpServletRequest request) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setRequestData("stuId", request.getParameter("stuId"));
		msgToView.setRequestData("jobId", request.getParameter("jobId"));
		msgToView.setRequestData("state", "2");
		msgToView = managerService.alterNewStuSend(msgToView);
		return msgToView;
	}

	// 设置感兴趣
	@RequestMapping("interestResume")
	@ResponseBody
	public MsgJson<String, Object> interestResume(HttpServletRequest request) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setRequestData("stuId", request.getParameter("stuId"));
		msgToView.setRequestData("jobId", request.getParameter("jobId"));
		msgToView.setRequestData("state", "3");
		msgToView = managerService.alterNewStuSend(msgToView);
		return msgToView;
	}
}
