package com.esen.youngcms.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.esen.youngcms.bean.SysUser;
import com.esen.youngcms.bean.SysUserRole;
import com.esen.youngcms.dao.SysUserMapper;
import com.esen.youngcms.dao.SysUserRoleMapper;

@Service
public class SysUserService extends ServiceImpl<SysUserMapper,SysUser> {
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	public SysUser selectByLoginName(String loginName) {
		SysUser sysUser=new SysUser();
		sysUser.setLoginName(loginName);
		return super.selectOne(new EntityWrapper<>(sysUser));
	}

	public void addOrUpdate(SysUser sysUser, String roleIds) {
		super.insertOrUpdate(sysUser);
		if (StringUtils.isNotBlank(roleIds)) {
			sysUserRoleMapper.deleteBySysUserId(sysUser.getId());
			String[] ids=StringUtils.split(roleIds,",");
			for (int i = 0; i < ids.length; i++) {
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setSysUserId(sysUser.getId());
				sysUserRole.setRoleId(Integer.valueOf(ids[i]));
				sysUserRoleMapper.insert(sysUserRole);
			}
		}
	}

}
