package com.esen.youngcms.core.utils.qiniu;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class ListItem {
	public String key;
	public String hash;
	public long fsize;
	public long putTime;
	public String mimeType;
	public String endUser;
	
	public ListItem() {
		
	}
	
	public ListItem(JSONObject obj) throws JSONException {
		this.unmarshal(obj);
	}

	private void unmarshal(JSONObject jsonObject) throws JSONException {
		if (jsonObject.containsKey("key")) {
			this.key = jsonObject.getString("key");
		}
		if (jsonObject.containsKey("hash")) {
			this.hash = jsonObject.getString("hash");
		}
		if (jsonObject.containsKey("fsize")) {
			this.fsize = jsonObject.getLong("fsize");
		}
		if (jsonObject.containsKey("putTime")) {
			this.putTime = jsonObject.getLong("putTime");
		}
		if (jsonObject.containsKey("mimeType")) {
			this.mimeType = jsonObject.getString("mimeType");
		}
		if (jsonObject.containsKey("endUser")) {
			this.endUser = jsonObject.getString("endUser");
		}
	}
}
