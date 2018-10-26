package com.esen.youngcms.core.utils.db;

public class DBContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	public static final String DB_MASTER="master";
	public static final String DB_SLAVE="slave";
	
	public static void setCustomerType(String customerType) {
		contextHolder.set(customerType);
	}

	public static String getCustomerType() {
		return (String) contextHolder.get();
	}

	public static void clearCustomerType() {
		contextHolder.remove();
	}
}
