package com.esen.youngcms.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.esen.youngcms.bean.OperateLog;
import com.esen.youngcms.controller.base.AdminBaseController;
import com.esen.youngcms.service.OperateLogService;
@Controller
@RequestMapping("/admin/operateLog/")
public class OperateLogController extends AdminBaseController{

     @Autowired
     private OperateLogService operateLogService;

     @RequestMapping("list")
     public String list(OperateLog bean,Model model) {
        Page<OperateLog> page=operateLogService.selectPage(getPage(), new EntityWrapper<>());
        model.addAttribute(DEFAULT_PAGE_DATA, getPageData(page, bean));
        return PREFIX+"operateLog/list";
     }
}
