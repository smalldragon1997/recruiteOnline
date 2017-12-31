package com.job.json;

import java.util.HashMap;
import java.util.List;

//传回前端的msg字符串
//传回前端和控制层的状态码
//传回控制层或传入业务层的session response request json自定义数据list   下标分别为0,1,2,3
public class MsgJson<K, V> {
	private String msg;
	private List<HashMap<K, V>> list;
	private boolean state;

	public MsgJson(String msg, List<HashMap<K, V>> list, boolean state) {
		super();
		this.msg = msg;
		this.list = list;
		this.state = state;
	}

	public MsgJson() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<HashMap<K, V>> getList() {
		return list;
	}

	public void setList(List<HashMap<K, V>> list) {
		this.list = list;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	// 对session response request json自定义数据list 的方法封装
	public void setSessionData(K key, V value) {
		this.list.get(0).put(key, value);
	}

	public V getSessionData(K key) {
		return this.list.get(0).get(key);
	}

	public HashMap<K, V> getSessionDataMap() {
		return this.list.get(0);
	}

	// response
	public void setResponseData(K key, V value) {
		this.list.get(1).put(key, value);
	}

	public V getResponseData(K key) {
		return this.list.get(1).get(key);
	}

	public HashMap<K, V> getResponseDataMap() {
		return this.list.get(1);
	}

	// request
	public void setRequestData(K key, V value) {
		this.list.get(2).put(key, value);
	}

	public V getRequestData(K key) {
		return this.list.get(2).get(key);
	}

	public HashMap<K, V> getRequestDataMap() {
		return this.list.get(2);
	}

	// 自定义json
	public void setJsonData(K key, V value) {
		this.list.get(3).put(key, value);
	}

	public V getJsonData(K key) {
		return this.list.get(3).get(key);
	}

	public HashMap<K, V> getJsonDataMap() {
		return this.list.get(3);
	}
}