package com.esen.youngcms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.esen.youngcms.bean.Content;

public interface ContentMapper extends BaseMapper<Content> {

	List<Content> selectPageRecords(RowBounds rowBounds,  @Param("ew")Wrapper<Content> wrapper);
}