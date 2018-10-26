package com.esen.youngcms.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", children=" + children + ", parent=" + parent + ", target="
				+ tabId + ", url=" + url + "]";
	}
	private Integer id;
	private String name;
	private List<Menu> children=new ArrayList<Menu>();
	private Menu parent;
	private String tabId;
	private String url;
	private String icon;
	private Integer isTree;
	private String treeUrl;
	private String external;
	
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
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getIsTree() {
		return isTree;
	}
	public void setIsTree(Integer isTree) {
		this.isTree = isTree;
	}
	public String getTreeUrl() {
		return treeUrl;
	}
	public void setTreeUrl(String treeUrl) {
		this.treeUrl = treeUrl;
	}
	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	public String getExternal() {
		return external;
	}
	public void setExternal(String external) {
		this.external = external;
	}
	

}
