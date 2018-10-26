package com.esen.youngcms.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.esen.youngcms.bean.ContentReptile;
import com.esen.youngcms.dao.ContentReptileMapper;
@Service
public class ContentReptileService extends ServiceImpl<ContentReptileMapper,ContentReptile> {

    
	/*@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void toContent(String ids, String channelId) throws Exception{
		String[] reptileIds=StringUtils.split(ids, ",");
		for(int i=0;i<reptileIds.length;i++){
			String reptileId=reptileIds[i];
			if(StringUtils.isNotBlank(reptileId)){
				ContentReptile contentReptile=contentReptileMapper.selectByPrimaryKey(Integer.valueOf(reptileId));
				Content content=new Content();
				content.setTitle(contentReptile.getTitle());
				content.setShortTitle(contentReptile.getTitle());
				content.setChannelId(Integer.valueOf(channelId));
				content.setClickNum(0);
				content.setCreateTime(DateUtil.dateToStr(new Date(), 12));
				content.setDescription(contentReptile.getDescription());
				content.setSource(contentReptile.getSource());
				content.setAuthor(contentReptile.getAuthor());
				content.setModelId(4);
				content.setTitleColor("#000000");
				content.setWeight(10);
				content.setIsTop(DictUtil.getIdByNameAndEnName("isTop","否"));
				content.setIsComment(DictUtil.getIdByNameAndEnName("isComment","是"));
				content.setTemplate("default/content/news_detail.html");
				contentMapper.insertSelective(content);
				ContentText contentText=new ContentText();
				contentText.setText(contentReptile.getContent());
				contentText.setContentId(content.getId());
				contentTextMapper.insertSelective(contentText);
				contentReptileMapper.deleteByPrimaryKey(Integer.valueOf(reptileId));
			}
		}
	}*/
	
	
}
