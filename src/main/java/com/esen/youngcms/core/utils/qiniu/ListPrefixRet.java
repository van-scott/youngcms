package com.esen.youngcms.core.utils.qiniu;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;


public class ListPrefixRet extends CallRet {
	public String marker;
	public List<ListItem> results = new ArrayList<ListItem>();
	
	public ListPrefixRet(CallRet ret) {
		super(ret);
		if (ret.ok() && ret.getResponse() != null) {
			try {
				unmarshal(ret.getResponse());
			} catch (Exception e) {
				e.printStackTrace();
				this.exception = e;
			}
		}
	}
	
	private void unmarshal(String response) throws JSONException {
		JSONObject obj = JSON.parseObject(response);
		if (obj.containsKey("marker")) {
			this.marker = obj.getString("marker");
		}
		JSONArray arr = obj.getJSONArray("items");
		for (int i = 0; i < arr.size(); i++) {
			JSONObject jsonObj = arr.getJSONObject(i);
			ListItem ret = new ListItem(jsonObj);
			results.add(ret);
		}
	}
	
}
