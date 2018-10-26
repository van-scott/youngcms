package com.esen.youngcms.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.esen.youngcms.bean.Dict;
import com.esen.youngcms.dao.DictMapper;
@Service
public class DictService extends ServiceImpl<DictMapper,Dict> {
	
	@Autowired
	private DictMapper dictMapper;
	
	public List<Dict> selectAll(Wrapper<Dict> wrapper) {
		return dictMapper.selectAll(wrapper);
	}

	public Page<Dict> selectPageRecords(Page<Dict> page,  Wrapper<Dict> wrapper) {
		SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(dictMapper.selectPageRecords(page, wrapper));
        return page;
	}
   
}
