package com.esen.youngcms.core.shiro;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.esen.youngcms.bean.FlowFace;
import com.esen.youngcms.bean.LoginLog;
import com.esen.youngcms.bean.Module;
import com.esen.youngcms.bean.Role;
import com.esen.youngcms.bean.Site;
import com.esen.youngcms.bean.SysUser;
import com.esen.youngcms.bean.SysUserRole;
import com.esen.youngcms.core.utils.Constants;
import com.esen.youngcms.core.utils.DateUtil;
import com.esen.youngcms.core.utils.HttpKit;
import com.esen.youngcms.core.utils.IPUtils;
import com.esen.youngcms.core.utils.SessionKey;
import com.esen.youngcms.core.utils.SortList;
import com.esen.youngcms.service.FlowFaceService;
import com.esen.youngcms.service.LoginLogService;
import com.esen.youngcms.service.ModuleService;
import com.esen.youngcms.service.RoleServiceCms;
import com.esen.youngcms.service.SiteService;
import com.esen.youngcms.service.SysUserRoleService;
import com.esen.youngcms.service.SysUserService;
import com.esen.youngcms.vo.Menu;

public class AuthRealm extends AuthorizingRealm{
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService ;
    @Autowired
    private RoleServiceCms roleService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private FlowFaceService flowFaceService;
    @Autowired
    private LoginLogService loginLogService;
    
    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;
        String username = utoken.getUsername();
        SysUser sysUser = sysUserService.selectByLoginName(username);
        Subject currentUser = SecurityUtils.getSubject();  
		Set<Integer> moduleIds=new HashSet<>();
		List<Module> moduleList=new ArrayList<Module>();
		List<Module> finalModuleList=new ArrayList<Module>();
		List<SysUserRole> sysUserRoles=sysUserRoleService.selectBySysUserId(sysUser.getId());
		if("administrator".equals(sysUser.getLoginName())){
			moduleList=moduleService.selectList(null);
		}else{
			for(SysUserRole sysUserRole:sysUserRoles){
				Role role=roleService.selectById((sysUserRole.getRoleId()));
				if(role!=null && StringUtils.isNotBlank(role.getModuleIds())){
					EntityWrapper<Module> entityWrapper=new EntityWrapper<>();
					entityWrapper.in("id", StringUtils.split(role.getModuleIds(),","));
					moduleList=moduleService.selectList(entityWrapper);
				}
			}
		}
		/**
		 * 去重
		 */
		for(Module module:moduleList){
			if(!moduleIds.contains(module.getId())){
				finalModuleList.add(module);
			}
		}
		/**
		 * 排序
		 */
		SortList<Module> sortList = new SortList<Module>();
	    sortList.Sort(finalModuleList, "getSort", "asc");
		List<Menu> menus=new ArrayList<Menu>();
		for (Module module : finalModuleList) {
			if (Constants.MODULU_TYPE_MENU.equals(module.getModuleType()) && module.getpId() == null) {
				Menu menu = new Menu();
				menu.setId(module.getId());
				menu.setName(module.getName());
				menu.setUrl(module.getHref());
				menu.setTabId(module.getTabId());
				menu.setIcon(module.getIcon());
				menu.setIsTree(module.getIsTree());
				menu.setTreeUrl(module.getTreeUrl());
				menu.setExternal(module.getExternal());
				menus.add(menu);
			}
		}
		
		
		currentUser.getSession(true).setAttribute(SessionKey.SYS_USER, sysUser);
		currentUser.getSession(true).setAttribute(SessionKey.SYS_ROLE, sysUserRoles);
		currentUser.getSession(true).setAttribute(SessionKey.SYS_USER_NAME, sysUser.getRealName());
		currentUser.getSession(true).setAttribute(SessionKey.SYS_MODULE, finalModuleList);
		currentUser.getSession(true).setAttribute(SessionKey.SYS_MENU, menus);
		/**
		 * 查询站点信息
		 */
		Site site=siteService.selectById(1);
		currentUser.getSession(true).setAttribute(SessionKey.SYS_SITE, site);
		/**
		 * 更新并且保存日志
		 */
		sysUser.setLastLoginTime(DateUtil.dateToStr(new Date(), 12));
		sysUserService.updateById(sysUser);
		LoginLog loginLog=new LoginLog();
		loginLog.setLoginName(sysUser.getLoginName());
		loginLog.setIp(IPUtils.getIpAddr(HttpKit.getRequest()));
		loginLog.setResult("成功");
		loginLog.setMsg("登陆成功");
		loginLog.setCreateTime(new Date());
		loginLogService.insert(loginLog);
        return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(),getName());
    }
    
    //授权
	@SuppressWarnings("unchecked")
	@Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		List<String> permissions=new ArrayList<>();
		Subject subject = SecurityUtils.getSubject();  
		List<Module> modules=(List<Module>) subject.getSession(true).getAttribute(SessionKey.SYS_MODULE);
        for (Module module : modules) {
        	if(StringUtils.isNotBlank(module.getAuthorize())){
        		permissions.add(module.getAuthorize());
        	}
		}
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;
    }

}