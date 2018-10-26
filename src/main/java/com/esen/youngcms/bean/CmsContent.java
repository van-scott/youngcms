package com.esen.youngcms.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName(value="tb_cms_content",resultMap="BaseResultMap")
public class CmsContent extends Model<CmsContent>{
	
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	@TableId(type=IdType.AUTO)
    private Integer id;

    private String clickNum;

    private String content;

    private String createTime;

    private String createUser;

    private String description;

    private String imgUrl;

    private String radioUrl;

    private String shortTitle;

    private String title;

    private Integer channelId;
    
    private String channelName;

    private Integer flowFaceValue;

    private Integer flowValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClickNum() {
        return clickNum;
    }

    public void setClickNum(String clickNum) {
        this.clickNum = clickNum == null ? null : clickNum.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public String getRadioUrl() {
        return radioUrl;
    }

    public void setRadioUrl(String radioUrl) {
        this.radioUrl = radioUrl == null ? null : radioUrl.trim();
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle == null ? null : shortTitle.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getFlowFaceValue() {
        return flowFaceValue;
    }

    public void setFlowFaceValue(Integer flowFaceValue) {
        this.flowFaceValue = flowFaceValue;
    }

    public Integer getFlowValue() {
        return flowValue;
    }

    public void setFlowValue(Integer flowValue) {
        this.flowValue = flowValue;
    }

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}