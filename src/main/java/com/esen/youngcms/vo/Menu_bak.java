package com.esen.youngcms.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu_bak implements Serializable{
	
	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", children=" + children + ", parent=" + parent + ", target="
				+ target + ", url=" + url + "]";
	}
	private Integer id;
	private String name;
	private List<Menu_bak> children=new ArrayList<Menu_bak>();
	private Menu_bak parent;
	private String target;
	private String url;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Menu_bak> getChildren() {
		return children;
	}
	public void setChildren(List<Menu_bak> children) {
		this.children = children;
	}
	public Menu_bak getParent() {
		return parent;
	}
	public void setParent(Menu_bak parent) {
		this.parent = parent;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	

}
