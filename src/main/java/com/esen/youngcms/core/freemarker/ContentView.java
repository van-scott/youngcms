package com.esen.youngcms.core.freemarker;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.esen.youngcms.bean.CmsModel;
import com.esen.youngcms.bean.Content;
import com.esen.youngcms.bean.ContentImage;
import com.esen.youngcms.bean.ContentOption;
import com.esen.youngcms.bean.ContentText;
import com.esen.youngcms.bean.ContentVideo;
import com.esen.youngcms.core.freemarker.utils.Freemarker;
import com.esen.youngcms.core.utils.DictUtil;
import com.esen.youngcms.service.ChannelService;
import com.esen.youngcms.service.CmsModelService;
import com.esen.youngcms.service.ContentImageService;
import com.esen.youngcms.service.ContentOptionService;
import com.esen.youngcms.service.ContentService;
import com.esen.youngcms.service.ContentTextService;
import com.esen.youngcms.service.ContentVideoService;
import com.esen.youngcms.vo.ContentDetail;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.WrappingTemplateModel;
/**
 * 内容详情
 * @author fumiao-pc
 *
 */
@Repository
public class ContentView implements TemplateDirectiveModel {
	
	@Autowired
	private ChannelService channelService;
	@Autowired
	private ContentService contentService;
	@Autowired
	private CmsModelService cmsModelService;
	@Autowired
	private ContentTextService contentTextService;
	@Autowired
	private ContentImageService contentImageService;
	@Autowired
	private ContentOptionService contentOptionService;
	@Autowired
	private ContentVideoService contentVideoService;

	@Override
	public void execute(Environment env, Map map, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			Integer contentId = Freemarker.getInteger(map, "contentId");
			Content content = contentService.selectById(contentId);
			CmsModel model = cmsModelService.selectById(content.getModelId());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("contentId", content.getId());
			ContentDetail contentDetail = new ContentDetail();
			BeanUtils.copyProperties(contentDetail, content);
			if (model.getHasContent() == DictUtil.getIdByNameAndEnName("hasContent", "是")) {
				List<ContentText> list = contentTextService.selectList(new EntityWrapper<>());
				if (list.size() > 0) {
					ContentText contentText =list.get(0);
					contentDetail.setContentText(contentText);
				}
			}
			if (model.getHasGroupImages() == DictUtil.getIdByNameAndEnName("hasGroupImages", "是")) {
				List<ContentImage> list = contentImageService.selectList(new EntityWrapper<>());
				contentDetail.setContentImages(list);
			}
			if (model.getHasVedio() == DictUtil.getIdByNameAndEnName("hasVedio", "是")) {
				List<ContentVideo> list = contentVideoService.selectList(new EntityWrapper<>());
				if (list.size() > 0) {
					ContentVideo contentVideo = list.get(0);
					contentDetail.setContentVideo(contentVideo);
				}
			}
			if (model.getHasOptions() == DictUtil.getIdByNameAndEnName("hasOptions", "是")) {
				List<ContentOption> list = contentOptionService.selectList(new EntityWrapper<>());
				contentDetail.setContentOptions(list);
			}
			loopVars[0] = WrappingTemplateModel.getDefaultObjectWrapper().wrap(contentDetail);
			body.render(env.getOut());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
