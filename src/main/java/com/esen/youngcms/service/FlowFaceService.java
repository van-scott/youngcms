package com.esen.youngcms.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.esen.youngcms.bean.FlowFace;
import com.esen.youngcms.dao.FlowFaceMapper;
@Service
public class FlowFaceService extends ServiceImpl<FlowFaceMapper,FlowFace> {

	public List<FlowFace> selectByFlowId(String flowId) {
		return null;
	}
}
