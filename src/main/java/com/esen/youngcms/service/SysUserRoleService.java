package com.esen.youngcms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.esen.youngcms.bean.SysUserRole;
import com.esen.youngcms.dao.SysUserRoleMapper;

@Service
public class SysUserRoleService extends ServiceImpl<SysUserRoleMapper,SysUserRole> {

	public List<SysUserRole> selectBySysUserId(Integer sysUserId) {
		SysUserRole sysUserRole=new SysUserRole();
		sysUserRole.setSysUserId(sysUserId);
		return super.selectList(new EntityWrapper<>(sysUserRole));
	}

}
