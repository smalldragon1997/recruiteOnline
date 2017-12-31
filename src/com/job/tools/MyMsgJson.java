// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 19:03:33
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MyMsgJson.java

package com.job.tools;

import com.job.json.MsgJson;
import java.util.*;
import javax.servlet.http.*;

public class MyMsgJson {

	public static void setData(MsgJson<String, Object> msgJson, Object object) {
		if (object instanceof HttpSession) {
			HashMap<String, Object> sessionHashMap = msgJson.getList().get(0);
			Set set = sessionHashMap.keySet();
			String key;
			Object value;
			for (Iterator iter = set.iterator(); iter.hasNext();) {
				key = (String) iter.next();
				value = sessionHashMap.get(key);
				if (key.equals("session") && value.equals("invalidate")) {
					((HttpSession) object).invalidate();
					break;
				}
				if (key.equals("remove")&&((HttpSession) object).getAttribute((String) value)!=null){
					((HttpSession) object).removeAttribute((String) value);
				}

				((HttpSession) object).setAttribute(key, value);
			}

		} else if (object instanceof HttpServletResponse) {
			// 获得msgJson中List下标为2的hashmap 下标为零代表取的是response cookie的name，value
			HashMap<String, Object> responseHashMap = msgJson.getList().get(1);
			// 获得map的key集合
			Set set = responseHashMap.keySet();
			// 遍历集合获得key的值
			Iterator iter = set.iterator();
			while (iter.hasNext()) {
				// 获得cookie 属性的name和value
				String name = (String) iter.next();
				// 获得cookievalue
				Cookie cookie = (Cookie) responseHashMap.get(name);
				// 添加到响应中
				((HttpServletResponse) object).addCookie(cookie);
			}
		} else {
			msgJson.setMsg(msgJson.getMsg() + " 但后台session或cookie未设置成功");
			System.out.println(msgJson.getMsg() + " 但后台session或cookie未设置成功");
		}
	}

	public static MsgJson<String, Object> newMsgjson() {

		HashMap<String, Object> sessionMap = new HashMap<String, Object>();
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		HashMap<String, Object> requestMap = new HashMap<String, Object>();
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		list.add(sessionMap);
		list.add(responseMap);
		list.add(requestMap);
		list.add(jsonMap);

		return new MsgJson<String, Object>("初始化成功", list, true);
	}
}
