package com.esen.youngcms.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.esen.youngcms.bean.SysUserRole;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

	void deleteBySysUserId(@Param("sysUserId")Integer sysUserId);

	
}