package com.job.controller;
// 用于安全请求 登录、注册

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.job.json.LoginJson;
import com.job.json.MsgJson;
import com.job.json.RegisterJson;
import com.job.service.SecurityService;
import com.job.tools.MyCookie;
import com.job.tools.MyMsgJson;

@Controller
@RequestMapping("security")
public class SecurityController {

	@Autowired
	@Qualifier(value = "securityService")
	private SecurityService securityService;

	// 登录
	@RequestMapping("login")
	@ResponseBody
	public MsgJson<String, Object> login(@RequestBody LoginJson loginJson, HttpSession session,
			HttpServletResponse response) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setJsonData("loginJson", loginJson);
		msgToView = securityService.login(msgToView);
		MyMsgJson.setData(msgToView, session);
		MyMsgJson.setData(msgToView, response);
		return msgToView;
	}

	// 注册
	@RequestMapping("register")
	@ResponseBody
	public MsgJson<String, Object> register(@RequestBody RegisterJson registerJson, HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setJsonData("registerJson", registerJson);
		msgToView = securityService.register(msgToView);
		MyMsgJson.setData(msgToView, session);
		return msgToView;
	}

	// 退出
	@RequestMapping("exit")
	@ResponseBody
	public MsgJson<String, Object> exit(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setRequestData("cookies", request.getCookies());
		msgToView = securityService.exit(msgToView);
		MyMsgJson.setData(msgToView, session);
		MyMsgJson.setData(msgToView, response);
		return msgToView;
	}

	// 修改密码
	@RequestMapping("updatePassword")
	public String updatePassword(HttpServletRequest request, HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setRequestData("oldPassword", request.getParameter("oldPassword"));
		msgToView.setRequestData("newPassword", request.getParameter("newPassword"));
		if (session.getAttribute("stuId") == null) {
			msgToView.setSessionData("manId", session.getAttribute("manId"));
		} else {
			msgToView.setSessionData("stuId", session.getAttribute("stuId"));
		}
		securityService.alterPwd(msgToView);
		return "platform";
	}
}
