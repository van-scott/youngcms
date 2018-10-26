package com.esen.youngcms.core.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.esen.youngcms.bean.FlowFace;
import com.esen.youngcms.vo.FlowBean;


/**
 * 工作流
 * @author fumiao
 *
 */
public class FlowUtil {
	
	public static String getNameByFlowIdAndStatus(String flowId,String status){
		List<FlowFace> flowFaces=FlowBean.getFlowFaces();
		for(FlowFace flowFace:flowFaces){
			if(flowFace.getFlowId().toString().equals(flowId) && flowFace.getSort().toString().equals(status)){
				return flowFace.getName();
			}
		}
		return "";
	}

	public static String[] getRolesByFlowIdAndStatus(String flowId, String status) {
		String[] roles={};
		List<FlowFace> flowFaces=FlowBean.getFlowFaces();
		for(FlowFace flowFace:flowFaces){
			if(flowFace.getFlowId().toString().equals(flowId) && flowFace.getSort().toString().equals(status)){
				roles=StringUtils.split(flowFace.getRoleIds(),",");
				return roles;
			}
		}
		return roles;
	}
}
