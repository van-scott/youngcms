package com.esen.youngcms.core.queue.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.esen.youngcms.bean.ReptileRule;
import com.esen.youngcms.core.utils.reptile.Reptile;
import com.esen.youngcms.service.ContentReptileService;
import com.esen.youngcms.service.ReptileRuleService;

/**
 * @author fumiao
 *
 * 抓取数据
 */
@Component
public class ReptileContentCommand extends BaseCommand{
	
	private  static Logger logger = LoggerFactory.getLogger(ReptileContentCommand.class);
	
	@Autowired
	private ReptileRuleService reptileRuleService;
	
	@Autowired
	private ContentReptileService contentReptileService;

	@Override
	public void execute(String params) {

		logger.info("==========抓取数据开始==============");
        try {
			JSONObject jsonObject=JSON.parseObject(params);
			Integer reptileRuleId=jsonObject.getInteger("reptileRuleId");
			ReptileRule reptileRule=reptileRuleService.selectById(reptileRuleId);
			Reptile reptile=new Reptile(reptileRule,contentReptileService);
			Thread thread=new Thread(reptile);
			thread.start();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

}
