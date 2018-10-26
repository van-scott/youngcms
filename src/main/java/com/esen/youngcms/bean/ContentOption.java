package com.esen.youngcms.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName(value="tb_content_option",resultMap="BaseResultMap")
public class ContentOption extends Model<ContentOption>{
	
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	@TableId(type=IdType.AUTO)
    private Integer id;

    private String title;

    private Integer clickNum;

    private String optionUrl;

    private Integer status;

    private Integer contentId;

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

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public String getOptionUrl() {
        return optionUrl;
    }

    public void setOptionUrl(String optionUrl) {
        this.optionUrl = optionUrl == null ? null : optionUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}