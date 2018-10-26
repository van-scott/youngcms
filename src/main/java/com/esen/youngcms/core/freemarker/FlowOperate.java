package com.esen.youngcms.core.freemarker;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Repository;

import com.esen.youngcms.bean.SysUserRole;
import com.esen.youngcms.core.utils.FlowUtil;
import com.esen.youngcms.core.utils.SessionKey;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
/**
 * 工作流
 * @author fumiao-pc
 *
 */
@Repository
public class FlowOperate implements TemplateMethodModel {
	
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		String flowId=(String) arguments.get(0);
		String status=(String) arguments.get(1);
		String url=(String) arguments.get(2);
		String statusName=FlowUtil.getNameByFlowIdAndStatus(flowId, status);
		String[] roles=FlowUtil.getRolesByFlowIdAndStatus(flowId, status);
		Subject currentUser = SecurityUtils.getSubject();  
		List<SysUserRole> sysUserRoles=(List<SysUserRole>) currentUser.getSession(true).getAttribute(SessionKey.SYS_ROLE);
		boolean b=false;
		for(String role:roles){
			for(SysUserRole sysUserRole:sysUserRoles){
				if(role.equals(sysUserRole.getRoleId().toString())){
					b=true;
					break; 
				}
			}
		}
		String str="";
		if(b){
			str="<a title='审核' target='ajaxTodo'  callback='ajaxTodoDone' href='"+url+"'>审核</a>";
		}
		return str;
	}
	

}
