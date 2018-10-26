package com.esen.youngcms.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName(value="tb_reptile_rule",resultMap="BaseResultMap")
public class ReptileRule  extends Model<ReptileRule>{
	
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	@TableId(type=IdType.AUTO)
    private Integer id;

    private String name;

    private String coding;

    private Integer number;

    private String timeFormat;

    private String reptileUrl;

    private String contentCompletionUrl;

    private String imageCompletionUrl;

    private Integer isReptileImage;

    private String titleStart;

    private String titleEnd;

    private String descriptionStart;

    private String descriptionEnd;

    private String contentStart;

    private String contentEnd;

    private String keywordsStart;

    private String keywordsEnd;

    private String publishTimeStart;

    private String publishTimeEnd;

    private String authorStart;

    private String authorEnd;

    private String sourceStart;

    private String sourceEnd;

    private String fromUrlStart;

    private String fromUrlEnd;

    private String readNumberStart;

    private String readNumberEnd;

    private String contentAllStart;

    private String contentAllEnd;

    private String createTime;

    private String createUser;

    private Integer reptileNumber;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding == null ? null : coding.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat == null ? null : timeFormat.trim();
    }

    public String getReptileUrl() {
        return reptileUrl;
    }

    public void setReptileUrl(String reptileUrl) {
        this.reptileUrl = reptileUrl == null ? null : reptileUrl.trim();
    }

    public String getContentCompletionUrl() {
        return contentCompletionUrl;
    }

    public void setContentCompletionUrl(String contentCompletionUrl) {
        this.contentCompletionUrl = contentCompletionUrl == null ? null : contentCompletionUrl.trim();
    }

    public String getImageCompletionUrl() {
        return imageCompletionUrl;
    }

    public void setImageCompletionUrl(String imageCompletionUrl) {
        this.imageCompletionUrl = imageCompletionUrl == null ? null : imageCompletionUrl.trim();
    }

    public Integer getIsReptileImage() {
        return isReptileImage;
    }

    public void setIsReptileImage(Integer isReptileImage) {
        this.isReptileImage = isReptileImage;
    }

    public String getTitleStart() {
        return titleStart;
    }

    public void setTitleStart(String titleStart) {
        this.titleStart = titleStart == null ? null : titleStart.trim();
    }

    public String getTitleEnd() {
        return titleEnd;
    }

    public void setTitleEnd(String titleEnd) {
        this.titleEnd = titleEnd == null ? null : titleEnd.trim();
    }

    public String getDescriptionStart() {
        return descriptionStart;
    }

    public void setDescriptionStart(String descriptionStart) {
        this.descriptionStart = descriptionStart == null ? null : descriptionStart.trim();
    }

    public String getDescriptionEnd() {
        return descriptionEnd;
    }

    public void setDescriptionEnd(String descriptionEnd) {
        this.descriptionEnd = descriptionEnd == null ? null : descriptionEnd.trim();
    }

    public String getContentStart() {
        return contentStart;
    }

    public void setContentStart(String contentStart) {
        this.contentStart = contentStart == null ? null : contentStart.trim();
    }

    public String getContentEnd() {
        return contentEnd;
    }

    public void setContentEnd(String contentEnd) {
        this.contentEnd = contentEnd == null ? null : contentEnd.trim();
    }

    public String getKeywordsStart() {
        return keywordsStart;
    }

    public void setKeywordsStart(String keywordsStart) {
        this.keywordsStart = keywordsStart == null ? null : keywordsStart.trim();
    }

    public String getKeywordsEnd() {
        return keywordsEnd;
    }

    public void setKeywordsEnd(String keywordsEnd) {
        this.keywordsEnd = keywordsEnd == null ? null : keywordsEnd.trim();
    }

    public String getPublishTimeStart() {
        return publishTimeStart;
    }

    public void setPublishTimeStart(String publishTimeStart) {
        this.publishTimeStart = publishTimeStart == null ? null : publishTimeStart.trim();
    }

    public String getPublishTimeEnd() {
        return publishTimeEnd;
    }

    public void setPublishTimeEnd(String publishTimeEnd) {
        this.publishTimeEnd = publishTimeEnd == null ? null : publishTimeEnd.trim();
    }

    public String getAuthorStart() {
        return authorStart;
    }

    public void setAuthorStart(String authorStart) {
        this.authorStart = authorStart == null ? null : authorStart.trim();
    }

    public String getAuthorEnd() {
        return authorEnd;
    }

    public void setAuthorEnd(String authorEnd) {
        this.authorEnd = authorEnd == null ? null : authorEnd.trim();
    }

    public String getSourceStart() {
        return sourceStart;
    }

    public void setSourceStart(String sourceStart) {
        this.sourceStart = sourceStart == null ? null : sourceStart.trim();
    }

    public String getSourceEnd() {
        return sourceEnd;
    }

    public void setSourceEnd(String sourceEnd) {
        this.sourceEnd = sourceEnd == null ? null : sourceEnd.trim();
    }

    public String getFromUrlStart() {
        return fromUrlStart;
    }

    public void setFromUrlStart(String fromUrlStart) {
        this.fromUrlStart = fromUrlStart == null ? null : fromUrlStart.trim();
    }

    public String getFromUrlEnd() {
        return fromUrlEnd;
    }

    public void setFromUrlEnd(String fromUrlEnd) {
        this.fromUrlEnd = fromUrlEnd == null ? null : fromUrlEnd.trim();
    }

    public String getReadNumberStart() {
        return readNumberStart;
    }

    public void setReadNumberStart(String readNumberStart) {
        this.readNumberStart = readNumberStart == null ? null : readNumberStart.trim();
    }

    public String getReadNumberEnd() {
        return readNumberEnd;
    }

    public void setReadNumberEnd(String readNumberEnd) {
        this.readNumberEnd = readNumberEnd == null ? null : readNumberEnd.trim();
    }

    public String getContentAllStart() {
        return contentAllStart;
    }

    public void setContentAllStart(String contentAllStart) {
        this.contentAllStart = contentAllStart == null ? null : contentAllStart.trim();
    }

    public String getContentAllEnd() {
        return contentAllEnd;
    }

    public void setContentAllEnd(String contentAllEnd) {
        this.contentAllEnd = contentAllEnd == null ? null : contentAllEnd.trim();
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

    public Integer getReptileNumber() {
        return reptileNumber;
    }

    public void setReptileNumber(Integer reptileNumber) {
        this.reptileNumber = reptileNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}