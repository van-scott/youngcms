package com.esen.youngcms.vo;

import java.io.Serializable;

public class CodeVO extends PageView<CodeVO> implements Serializable {
	
	private String clazzName;
	private Integer serviceExist=0;
	private Integer serviceImplExist=0;
	private Integer daoExist=0;
	private Integer daoImplExist=0;
	public String getClazzName() {
		return clazzName;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	public Integer getServiceExist() {
		return serviceExist;
	}
	public void setServiceExist(Integer serviceExist) {
		this.serviceExist = serviceExist;
	}
	public Integer getServiceImplExist() {
		return serviceImplExist;
	}
	public void setServiceImplExist(Integer serviceImplExist) {
		this.serviceImplExist = serviceImplExist;
	}
	public Integer getDaoExist() {
		return daoExist;
	}
	public void setDaoExist(Integer daoExist) {
		this.daoExist = daoExist;
	}
	public Integer getDaoImplExist() {
		return daoImplExist;
	}
	public void setDaoImplExist(Integer daoImplExist) {
		this.daoImplExist = daoImplExist;
	}
	@Override
	public String toString() {
		return "CodeVO [clazzName=" + clazzName + ", serviceExist="
				+ serviceExist + ", serviceImplExist=" + serviceImplExist
				+ ", daoExist=" + daoExist + ", daoImplExist=" + daoImplExist
				+ "]";
	}

}
