package com.job.interceptor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RoleInterceptor extends HttpServlet implements HandlerInterceptor {

	//用于身份验证的拦截器
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		 System.out.println("SecurityInterceptor...preHandle...");
	        //这里可以根据session的用户来判断角色的权限，根据权限来转发不同的页面
	        if(request.getSession().getAttribute("userId") == null) {
	            request.getRequestDispatcher("/login").forward(request,response);
	            return false;
	        }
	        return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
