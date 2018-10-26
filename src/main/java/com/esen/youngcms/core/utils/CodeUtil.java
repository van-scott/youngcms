package com.esen.youngcms.core.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CodeUtil {
	
	public static String getDaoCode(String clazzName) {
		
		 String code="package com.esen.youngcms.dao;\r\n"
				 
				+"import com.esen.youngcms.bean."+clazzName+";\r\n"
				+"import com.esen.youngcms.dao.base.DaoSupport;\r\n"
				
				+"public interface "+clazzName+"Dao extends DaoSupport<"+clazzName+"> {\r\n"
				
				+"}\r\n";
		 
		return code;
			
	   }
	public static String getDaoImplCode(String clazzName) {
		String code="package com.esen.youngcms.dao.impl;\r\n"
				+"import org.springframework.stereotype.Repository;\r\n"
				+"import com.esen.youngcms.bean."+clazzName+";\r\n"
				+"import com.esen.youngcms.dao."+clazzName+"Dao;\r\n"
				+"import com.esen.youngcms.dao.base.impl.DaoSupportImpl;\r\n"
				+"@Repository\r\n"
				+"public class "+clazzName+"DaoImpl extends DaoSupportImpl<"+clazzName+"> implements "+clazzName+"Dao {\r\n"
				
				+"}\r\n";
		return code;
		}
		
	public static String getServiceCode(String clazzName) {
			
			String code="package com.esen.youngcms.service;\r\n"
					
			           +"import com.esen.youngcms.bean."+clazzName+";\r\n"
			           +"import com.esen.youngcms.service.base.ServiceSupport;\r\n"
			
			           +"public interface "+clazzName+"Service extends ServiceSupport<"+clazzName+"> {\r\n"
			           
			           +"}\r\n";
			return code;
			
		}
		
	public static String getServiceImplCode(String clazzName) {
			
		 String code="package com.esen.youngcms.service.impl;\r\n"

			        +"import org.springframework.beans.factory.annotation.Autowired;\r\n"
			        +"import org.springframework.stereotype.Service;\r\n"

					+"import com.esen.youngcms.bean."+clazzName+";\r\n"
					+"import com.esen.youngcms.dao."+clazzName+"Dao;\r\n"
					+"import com.esen.youngcms.dao.base.DaoSupport;\r\n"
					+"import com.esen.youngcms.service."+clazzName+"Service;\r\n"
					+"import com.esen.youngcms.service.base.impl.ServiceSupportImpl;\r\n"
			        +"@Service\r\n"
			        +"public class "+clazzName+"ServiceImpl extends ServiceSupportImpl<"+clazzName+"> implements "+clazzName+"Service {\r\n"

				    +"   @Autowired\r\n"
				    +"   private "+clazzName+"Dao "+clazzName.replaceFirst(clazzName.substring(0, 1),clazzName.substring(0, 1).toLowerCase())+"Dao;\r\n"
				
				    +"   @Override\r\n"
				    +"   public DaoSupport<"+clazzName+"> daoSupport() {\r\n"
					+"      return  (DaoSupport<"+clazzName+">) "+clazzName.replaceFirst(clazzName.substring(0, 1),clazzName.substring(0, 1).toLowerCase())+"Dao;\r\n"
				    +"  }\r\n"
				
			        +"}\r\n";
			
			return code;
		}
	public static String getControllerCode(String clazzName) {
		
	 String clazzLowerName=clazzName.replaceFirst(clazzName.substring(0, 1),clazzName.substring(0, 1).toLowerCase());
	 String code="package com.esen.youngcms.controller.admin;\r\n"
				+"import javax.servlet.http.HttpServletRequest;\r\n"
				+"import org.springframework.beans.factory.annotation.Autowired;\r\n"
				+"import org.springframework.stereotype.Controller;\r\n"
				+"import org.springframework.web.bind.annotation.ModelAttribute;\r\n"
				+"import org.springframework.web.bind.annotation.RequestMapping;\r\n"
				+"import org.springframework.web.bind.annotation.ResponseBody;\r\n"
				+"import org.springframework.web.bind.annotation.SessionAttributes;\r\n"
				+"import org.springframework.web.bind.support.SessionStatus;\r\n"
				+"import org.springframework.web.servlet.ModelAndView;\r\n"
				+"import com.esen.youngcms.bean."+clazzName+";\r\n"
				+"import com.esen.youngcms.controller.base.AdminBaseController;\r\n"
				+"import com.esen.youngcms.service."+clazzName+"Service;\r\n"
				+"import com.esen.youngcms.vo.ActionResult;\r\n"
				+"import com.esen.youngcms.vo.QueryResult;\r\n"
				
				+"@Controller\r\n"
				+"@RequestMapping(\"/admin/"+clazzLowerName+"/\")\r\n"
				+"@SessionAttributes(\"pageForm\")\r\n"
				+"public class "+clazzName+"Controller extends AdminBaseController{\r\n"
				+"\r\n"
				+"     @Autowired\r\n"
				+"     private "+clazzName+"Service "+clazzLowerName+"Service;\r\n"
				+"\r\n"
				+"     @RequestMapping(\"list\")\r\n"
				+"     public ModelAndView list("+clazzName+" "+clazzLowerName+",HttpServletRequest request) throws Exception{\r\n"
				+"        QueryResult<"+clazzName+"> queryResult="+clazzLowerName+"Service.list("+clazzLowerName+".getFirstResult(), "+clazzLowerName+".getPageRow());\r\n"
				+"        "+clazzLowerName+".setPageDate(queryResult.getQueryResult());\r\n"
				+"        "+clazzLowerName+".setTotalCount(queryResult.getCount());\r\n"
				+"        ModelAndView mnv = new ModelAndView();\r\n"
				+"        mnv.addObject(DEFAULT_PAGE_VIEW,"+clazzLowerName+");\r\n"
				+"        mnv.setViewName(\"admin/"+clazzLowerName+"/list\");\r\n"
				+"        return mnv;\r\n"
				+"     }\r\n"
				+"\r\n"
				+"     @RequestMapping(\"delete\")\r\n"
				+"     @ResponseBody\r\n"
				+"     public ActionResult delete("+clazzName+" "+clazzLowerName+",HttpServletRequest request) throws Exception{\r\n"
				+"        ActionResult result=new ActionResult();\r\n"
				+"       try {\r\n"
				+"	     "+clazzLowerName+"Service.delete("+clazzLowerName+".getId());\r\n"
				+"	     result.setSuccess(true);\r\n"
				+"	     result.setMessage(RESULE_SUCCESS_DELETE);\r\n"
				+"		 } catch (Exception e) {\r\n"
				+"			result.setSuccess(false);\r\n"
				+"			result.setMessage(RESULE_ERROR_DELETE);\r\n"
				+"		}\r\n"
				+"		return result;\r\n"
				+"	 }\r\n"
				+"\r\n"
				+"	 @RequestMapping(\"showForm\")\r\n"
				+"	 public ModelAndView addForm("+clazzName+" "+clazzLowerName+",HttpServletRequest request) throws Exception{\r\n"
				+"		ModelAndView mnv = new ModelAndView();\r\n"
				+"		if("+clazzLowerName+".getId()!=null){\r\n"
				+"			"+clazzLowerName+"="+clazzLowerName+"Service.findById("+clazzLowerName+".getId());\r\n"
				+"		}\r\n"
				+"		mnv.addObject(DEFAULT_PAGE_FORM, "+clazzLowerName+");\r\n"
				+"		mnv.setViewName(\"admin/"+clazzLowerName+"/form\");\r\n"
				+"		return mnv;\r\n"
				+"	 }\r\n"
				+"\r\n"
				+"	@RequestMapping(\"addOrUpdate\")\r\n"
				+"	@ResponseBody\r\n"
				+"	public ActionResult add(@ModelAttribute(DEFAULT_PAGE_FORM)"+clazzName+" "+clazzLowerName+",HttpServletRequest request,SessionStatus sessionStatus) throws Exception{\r\n"
				+"		ActionResult result=new ActionResult();\r\n"
				+"		try {\r\n"
				+"			"+clazzLowerName+"Service.saveOrUpdate("+clazzLowerName+");\r\n"
				+"			result.setSuccess(true);\r\n"
				+"			result.setMessage(RESULE_SUCCESS);\r\n"
				+"		} catch (Exception e) {\r\n"
				+"			result.setSuccess(false);\r\n"
				+"			result.setMessage(RESULE_ERROR);\r\n"
				+"		}\r\n"
				+"	    sessionStatus.setComplete();\r\n"
				+"		return result;\r\n"
				+"	}\r\n"
				+"}\r\n";
	      return code;
		}
	
	public static String getListView(String clazzName) {
	 String clazzLowerName=clazzName.replaceFirst(clazzName.substring(0, 1),clazzName.substring(0, 1).toLowerCase());
	 List<String> properties=getProperties(clazzName);
     String code="<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\r\n"
				+"<%@ include file=\"/view/common/basePath.jsp\" %>\r\n"
				+"<%@ include file=\"/view/admin/common/import.jsp\" %>\r\n"
				+"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
				+"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n"
				+"<head>\r\n"
				+"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n"
				+"<title>无标题文档</title>\r\n"
				+"</head>\r\n"
				+"<body>\r\n"
				+"    <form:form action=\"#\" id=\"listForm\" name=\"listForm\">\r\n"
				+"    <jsp:include page=\"/view/admin/common/place.jsp\" />\r\n"
				+"    <div class=\"rightinfo\">\r\n"
				+"    <div class=\"tools\">\r\n"
				+"    	<ul class=\"toolbar\">\r\n"
				+"	        <li onclick=\"showForm('用户添加','${path}/admin/"+clazzLowerName+"/showForm')\"><span><img src=\"/view/admin/resource/images/t01.png\" /></span>添加</li>\r\n"
				+"        </ul>\r\n"
				+"        <ul class=\"toolbar1\">\r\n"
				+"        <li><span><img src=\"/view/admin/resource/images/t05.png\" /></span>设置</li>\r\n"
				+"        </ul>\r\n"
				+"    </div>\r\n"
				+"    <table class=\"tablelist\">\r\n"
				+"    	<thead>\r\n"
				+"	    	<tr>\r\n"
				+"	        <th><input type=\"checkbox\" checked=\"checked\"/></th>\r\n";
				for(int i=0;i<properties.size();i++){
				code+="	        <th>"+properties.get(i)+"</th>\r\n";
				}
		        code+="         <th>操作</th>"
				+"	        </tr>\r\n"
				+"       </thead>\r\n"
				+"        <tbody>\r\n"
				+"        <c:forEach items=\"${pageView.pageDate }\" var=\"bean\">\r\n"
				+"	        <tr>\r\n"
				+"	        <td><input name=\"id\" type=\"checkbox\" value=\"\" /></td>\r\n";
				for(int i=0;i<properties.size();i++){
				code+="	        <td>${bean."+properties.get(i)+" }</td>\r\n";
					}
				code+="	        <td>\r\n"
				+"	            <a href=\"javascript:showForm('用户修改','${path}/admin/"+clazzLowerName+"/showForm?id=${bean.id }')\" class=\"tablelink\">修改</a>\r\n"
				+"	            <a href=\"javascript:del('${path }/admin/"+clazzLowerName+"/delete?id=${bean.id}')\" class=\"tablelink\">删除</a>\r\n"
				+"	        </td>\r\n"
				+"	        </tr>\r\n"
				+"       </c:forEach>\r\n"
				+"        </tbody>\r\n"
				+"    </table>\r\n"
				+"   <jsp:include page=\"/view/admin/common/pageinfo.jsp\" />\r\n"
				+"    </div>\r\n"
				+"    </form:form>\r\n"
				+"    <script type=\"text/javascript\">\r\n"
				+"	$('.tablelist tbody tr:odd').addClass('odd');\r\n"
				+"	</script>\r\n"
				+"</body>\r\n"
				+"</html>\r\n";
		return code;
	}
	
	public static String getFormView(String clazzName) {
	 String clazzLowerName=clazzName.replaceFirst(clazzName.substring(0, 1),clazzName.substring(0, 1).toLowerCase());
	 List<String> properties=getProperties(clazzName);
     String code="<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\r\n"
				+"<%@ include file=\"/view/common/basePath.jsp\" %>\r\n"
				+"<%@ include file=\"/view/admin/common/import.jsp\" %>\r\n"
				+"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
				+"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n"
				+"<head>\r\n"
				+"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n"
				+"<title>无标题文档</title>\r\n"
				+"</head>\r\n"
				+"<body>\r\n"
				+"    <form:form id=\"form_add_update\" name=\"form_add_update\" modelAttribute=\"pageForm\">\r\n"
				+"      <table class='formtable'>\r\n"
			    +"        <tr>\r\n"
			    +"          <td colspan=\"2\"><div class=\"formtitle\"><span>基本信息</span></div></td>\r\n"
			    +"        </tr>\r\n";
                  for(int i=0;i<properties.size();i++){
                	  if("id".equals(properties.get(i))){
                		  continue;
                	  }
            code+="       <tr>\r\n";
            code+="          <td>title</td>\r\n";
            code+="          <td><form:input path=\""+properties.get(i)+"\" cssClass=\"dfinput\"/></td>\r\n";
            code+="       </tr>\r\n";
                   }
            code+="       <tr>\r\n"
                +"           <td>&nbsp;</td>\r\n"
			    +"           <td><input type=\"button\" class=\"btn\" value=\"确认保存\" onclick=\"addOrUpdate('form_add_update','${path}/admin/"+clazzLowerName+"/addOrUpdate')\"/></td>\r\n"
			    +"        </tr>\r\n"
			    +"     </form:form>\r\n"
			    +"</body>\r\n"
				+"</html>\r\n";
		return code;
	}
	
   private static List<String>  getProperties(String clazzName){
	String clazzPath="com.esen.youngcms.bean."+clazzName+"";
	List<String> list=new ArrayList<String>();
    try {
		Field[] fields=Class.forName(clazzPath).getDeclaredFields();
		for(Field field:fields){
			list.add(field.getName());
		}
	} catch (Exception e) {
		e.printStackTrace();
	} 
	return list;
   }
}
