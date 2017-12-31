// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 19:01:56
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MyCookie.java

package com.job.tools;

import javax.servlet.http.Cookie;

public  class MyCookie {
	public static Cookie getCookie(Cookie cookies[],String cookieName){
		Cookie cookie = null;
		// 遍历cookie数组
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals(cookieName)) {
					return cookie;
				}
			}
		}
		//无匹配 返回空
		return null;
	}
}