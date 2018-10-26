package com.esen.youngcms.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.esen.youngcms.bean.Dict;
/**
 * 加载字典表
 * @author fumiao
 */
public class DictBean implements Serializable {
	
	private static List<Dict> dicts=new ArrayList<Dict>();

	public static List<Dict> getDicts() {
		return dicts;
	}

	public static void setDicts(List<Dict> dicts) {
		DictBean.dicts = dicts;
	}

}
