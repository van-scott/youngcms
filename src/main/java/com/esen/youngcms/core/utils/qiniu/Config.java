package com.esen.youngcms.core.utils.qiniu;

import com.esen.youngcms.core.utils.Global;

/**
 * The Config class is a global configuration file for the sdk, used for serve
 * side only.
 */
public class Config {
	
	public static String USER_AGENT;
	
	public static String ACCESS_KEY =Global.getConfig("QINIU_ACCESS_KEY"); 
	
	public static String SECRET_KEY =Global.getConfig("QINIU_SECRET_KEY"); 

	public static String RS_HOST = "http://rs.qbox.me";

	public static String UP_HOST = "http://up.qbox.me";
	
	public static String RSF_HOST = "http://rsf.qbox.me";

}
