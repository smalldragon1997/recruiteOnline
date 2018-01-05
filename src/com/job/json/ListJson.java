package com.job.json;

import java.util.List;

public class ListJson<E> {
	private NameLinkJson parent;
	private List<E> childList;
	public ListJson(NameLinkJson parent, List<E> childList) {
		super();
		this.parent = parent;
		this.childList = childList;
	}
	public ListJson() {
		super();
	}
	public NameLinkJson getParent() {
		return parent;
	}
	public void setParent(NameLinkJson parent) {
		this.parent = parent;
	}
	public List<E> getChildList() {
		return childList;
	}
	public void setChildList(List<E> childList) {
		this.childList = childList;
	}
	
	
}
