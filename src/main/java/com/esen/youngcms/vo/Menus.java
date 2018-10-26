package com.esen.youngcms.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menus implements Serializable{
	
	private String id;
	
	private List<Menu> menu;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
	

}
