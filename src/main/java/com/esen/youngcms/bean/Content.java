package com.esen.youngcms.bean;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName(value="tb_content",resultMap="BaseResultMap")
public class Content extends Model<Content>  {
	
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	@TableId(type=IdType.AUTO)
    private Integer id;

    private String title;

    private String shortTitle;

    private Integer clickNum;

    private String createTime;

    private String createUser;

    private String description;

    private String imgUrl;

    private Integer channelId;

    private Integer flowId;

    private String tags;

    private String source;

    private String author;

    private String startTime;

    private String endTime;

    private String titleColor;

    private Integer isBold;

    private Integer weight;

    private String updateTime;

    private Integer status;

    private String href;

    private Integer isTop;

    private Integer isComment;

    private Integer modelId;

    private String template;
    
    @TableField(exist=false)
    private String channelName;
    
    @TableField(exist=false)
    private String modelName;
    
    @TableField(exist=false)
    private String content;
    
    @TableField(exist=false)
    private ContentText contentText;
    
    @TableField(exist=false)
    private ContentVideo contentVideo;
    
    @TableField(exist=false)
    private List<ContentImage> contentImages;
    
    @TableField(exist=false)
    private List<ContentOption> contentOptions;
    
    public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle == null ? null : shortTitle.trim();
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor == null ? null : titleColor.trim();
    }

    public Integer getIsBold() {
        return isBold;
    }

    public void setIsBold(Integer isBold) {
        this.isBold = isBold;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getIsComment() {
        return isComment;
    }

    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ContentText getContentText() {
		return contentText;
	}

	public void setContentText(ContentText contentText) {
		this.contentText = contentText;
	}

	public ContentVideo getContentVideo() {
		return contentVideo;
	}

	public void setContentVideo(ContentVideo contentVideo) {
		this.contentVideo = contentVideo;
	}

	public List<ContentImage> getContentImages() {
		return contentImages;
	}

	public void setContentImages(List<ContentImage> contentImages) {
		this.contentImages = contentImages;
	}

	public List<ContentOption> getContentOptions() {
		return contentOptions;
	}

	public void setContentOptions(List<ContentOption> contentOptions) {
		this.contentOptions = contentOptions;
	}

	public Integer getFlowId() {
		return flowId;
	}

	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}