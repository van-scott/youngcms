package com.esen.youngcms.core.freemarker;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.esen.youngcms.core.utils.FlowUtil;
import com.esen.youngcms.service.FlowFaceService;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
/**
 * 工作流
 * @author fumiao-pc
 *
 */
@Repository
public class FlowStatusName implements TemplateMethodModel {
	
	@Autowired
	private FlowFaceService flowFaceService;

	@Override
	public Object exec(List arguments) throws TemplateModelException {
		String flowId=(String) arguments.get(0);
		String status=(String) arguments.get(1);
		String statusName=FlowUtil.getNameByFlowIdAndStatus(flowId, status);
		if(StringUtils.isBlank(statusName) && "0".equals(status)){
			statusName="正常";
		}
		return statusName;
	}
	

}
