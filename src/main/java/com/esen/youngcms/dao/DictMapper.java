package com.esen.youngcms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.esen.youngcms.bean.Dict;

public interface DictMapper extends BaseMapper<Dict> {
	
	public List<Dict> selectAll(@Param("ew") Wrapper<Dict> wrapper);

	public List<Dict> selectPageRecords(RowBounds rowBounds,  @Param("ew")Wrapper<Dict> wrapper);
	
}