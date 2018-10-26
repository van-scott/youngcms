package com.esen.youngcms.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.LoginLog;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.service.LoginLogService;
@Controller
@RequestMapping("/admin/loginLog/")
public class LoginLogController extends AdminBaseController{

     @Autowired
     private LoginLogService loginLogService;

     @RequestMapping("list")
     public String list(LoginLog bean,Model model) {
        Page<LoginLog> page=loginLogService.selectPage(getPage());
        model.addAttribute(DEFAULT_PAGE_DATA,getPageData(page, bean));
        return "admin/loginLog/list";
     }

}
