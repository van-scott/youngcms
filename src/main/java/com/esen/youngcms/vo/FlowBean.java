package com.esen.youngcms.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.esen.youngcms.bean.Flow;
import com.esen.youngcms.bean.FlowFace;
/**
 * 加载工作流信息
 * @author fumiao
 */
public class FlowBean implements Serializable {
	
	private static List<Flow> flows=new ArrayList<Flow>();
	
	private static List<FlowFace> flowFaces=new ArrayList<FlowFace>();

	public static List<Flow> getFlows() {
		return flows;
	}

	public static void setFlows(List<Flow> flows) {
		FlowBean.flows = flows;
	}

	public static List<FlowFace> getFlowFaces() {
		return flowFaces;
	}

	public static void setFlowFaces(List<FlowFace> flowFaces) {
		FlowBean.flowFaces = flowFaces;
	}


}
