package com.esen.youngcms.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esen.youngcms.bean.SysUser;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.core.utils.ImgCode;
import com.esen.youngcms.core.utils.SessionKey;
import com.esen.youngcms.vo.Callback;

@Controller
@RequestMapping("/admin/")
public class AdminLoginController extends AdminBaseController{
	
	@RequestMapping(value="loginForm",method = RequestMethod.GET)
	public String loginForm(SysUser sysUser,HttpServletRequest request,Model model) {
		return "admin/login";
	}
	
	@RequestMapping("login")
	@ResponseBody
	public Callback login(SysUser sysUser, HttpServletRequest request, ModelMap mode) {
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(sysUser.getLoginName(), sysUser.getPassword());
		SecurityUtils.getSubject().login(usernamePasswordToken);
		return new Callback(true, "登陆成功");
	}
	
	@RequestMapping("loginOut")
	public String  loginOut(HttpServletRequest request,ModelMap mode) {
		request.getSession(true).removeAttribute(SessionKey.SYS_USER);
		request.getSession(true).removeAttribute(SessionKey.SYS_ROLE);
		request.getSession(true).removeAttribute(SessionKey.SYS_USER_NAME);
		request.getSession(true).removeAttribute(SessionKey.SYS_MODULE);
		request.getSession(true).removeAttribute(SessionKey.SYS_MENU);
		return "forward:/admin/loginForm";
		
	}
	
	@RequestMapping("getCode")
	public void  getCode(HttpServletRequest request,HttpServletResponse response,ModelMap mode) {
		ImgCode imgCode=new ImgCode();
		imgCode.getRandcode(request,response);
	}

}
