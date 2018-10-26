package com.esen.youngcms.core.utils.qiniu;

import java.util.UUID;

import com.alibaba.fastjson.JSONException;
import com.esen.youngcms.core.utils.Global;

public class Uptoken {
	
	public final static String makeUptoken(String bucketName) throws AuthException,
			JSONException {
		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		PutPolicy putPolicy = new PutPolicy(bucketName);
		// 可以根据自己需要设置过期时间,sdk默认有设置，具体看源码
		// putPolicy.expires = getDeadLine();
		//FIXME 回掉地址
		putPolicy.returnUrl =Global.getConfig("returnUrl");
		putPolicy.returnBody = "{\"name\": $(fname),\"size\": \"$(fsize)\",\"w\": \"$(imageInfo.width)\",\"h\": \"$(imageInfo.height)\",\"key\":$(etag),\"path\":$(x:path)}";
		String uptoken = putPolicy.token(mac);
		return uptoken;
	}

	/**
	 * 生成32位UUID 并去掉"-"
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
