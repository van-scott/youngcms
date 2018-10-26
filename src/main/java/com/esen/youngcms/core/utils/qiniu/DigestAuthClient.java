package com.esen.youngcms.core.utils.qiniu;

import org.apache.http.client.methods.HttpPost;

public class DigestAuthClient extends Client {
	public Mac mac;
	
	public DigestAuthClient(Mac mac) {
		this.mac = mac;
	}

	@Override
	public void setAuth(HttpPost post) throws AuthException {
		String accessToken = mac.signRequest(post);
		post.setHeader("Authorization", "QBox " + accessToken);
	}

}
