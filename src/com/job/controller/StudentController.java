package com.job.controller;
// 用于学生用户的请求 管理个人信息、投简

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.job.entity.Info;
import com.job.json.MsgJson;
import com.job.service.PublicService;
import com.job.service.StudentService;
import com.job.tools.MyMsgJson;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	@Qualifier(value = "studentService")
	private StudentService studentService;

	@Autowired
	@Qualifier(value = "publicService")
	private PublicService publicService;

	// 投简
	@RequestMapping("resume")
	@ResponseBody
	public MsgJson<String, Object> resume(HttpServletRequest request, HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setRequestData("jobId", request.getParameter("jobId"));
		msgToView.setSessionData("stuId", session.getAttribute("stuId"));
		return studentService.addStuSend(msgToView);
	}

	// 查看已处理投简
	@RequestMapping("getOldResumedJobs")
	@ResponseBody
	public MsgJson<String, Object> getResumedJobs(HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setSessionData("stuId", session.getAttribute("stuId"));
		return studentService.getOldStuSendJob(msgToView);
	}

	// 查看未处理投简
	@RequestMapping("getNewResumedJobs")
	@ResponseBody
	public MsgJson<String, Object> getNewResumedJobs(HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setSessionData("stuId", session.getAttribute("stuId"));
		return studentService.getNewStuSendJob(msgToView);
	}

	// 查看被感兴趣投简
	@RequestMapping("getInterestResumedJobs")
	@ResponseBody
	public MsgJson<String, Object> getInterestResumedJobs(HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setSessionData("stuId", session.getAttribute("stuId"));
		return studentService.getInterestStuSendJob(msgToView);
	}

	// 删除已投简
	@RequestMapping("deleteResumed")
	@ResponseBody
	public MsgJson<String, Object> deleteResumed(HttpServletRequest request, HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setRequestData("jobId", request.getParameter("jobId"));
		msgToView.setSessionData("stuId", session.getAttribute("stuId"));
		return studentService.dropStuSend(msgToView);
	}


	// 查看学生信息
	@RequestMapping("getStudentInfo")
	@ResponseBody
	public MsgJson<String, Object> getStudentInfo( HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setSessionData("stuId", session.getAttribute("stuId"));
		return publicService.getStuInfo(msgToView);
	}


	// 设置个人信息
	@RequestMapping("addStuInfo")
	public String addStuInfo(HttpServletRequest request, HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		int stuId = (Integer) session.getAttribute("stuId");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String major = request.getParameter("major");
		String birthday = request.getParameter("birthday");
		String majorClass = request.getParameter("majorClass");
		String rewards = request.getParameter("rewards");
		String resume = request.getParameter("resume");
		int exceptSal = Integer.parseInt(request.getParameter("exceptSal"));
		Info info = new Info(stuId,name,sex,major,birthday,majorClass,rewards,resume,exceptSal);
		msgToView.setJsonData("info", info);
		studentService.addStuInfo(msgToView);
		return "platform";
	}

	// 修改个人信息
	@RequestMapping("updateStuInfo")
	public String updateStuInfo(HttpServletRequest request, HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		int stuId = (Integer) session.getAttribute("stuId");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String major = request.getParameter("major");
		String birthday = request.getParameter("birthday");
		String majorClass = request.getParameter("majorClass");
		String rewards = request.getParameter("rewards");
		String resume = request.getParameter("resume");
		int exceptSal = Integer.parseInt(request.getParameter("exceptSal"));
		Info info = new Info(stuId,name,sex,major,birthday,majorClass,rewards,resume,exceptSal);
		msgToView.setJsonData("info", info);
		studentService.alterStuInfo(msgToView);
		return "platform";
	}

}
