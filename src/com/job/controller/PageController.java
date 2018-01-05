package com.job.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.job.json.MsgJson;
import com.job.service.SecurityService;
import com.job.tools.MyCookie;
import com.job.tools.MyMsgJson;

// 用于页面跳转
@RequestMapping("page")
@Controller
public class PageController {

	@Autowired
	@Qualifier(value = "securityService")
	private SecurityService securityService;

	@RequestMapping("main")
	public String mainPage() {
		return "platform";
	}

	// 免登录
	@RequestMapping("login")
	public String freeLogin(HttpServletRequest request, HttpSession session) {
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		msgToView.setSessionData("isLogin", session.getAttribute("isLogin"));
		msgToView.setRequestData("cookies", request.getCookies());
		msgToView = securityService.freeLogin(msgToView);
		MyMsgJson.setData(msgToView, session);
		return (String) msgToView.getJsonData("page");
	}
}
