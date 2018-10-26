package com.esen.youngcms.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName(value="tb_cms_model",resultMap="BaseResultMap")
public class CmsModel  extends com.baomidou.mybatisplus.activerecord.Model<CmsModel>{
	
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	@TableId(type=IdType.AUTO)
    private Integer id;

    private String name;

    private String tempPrefix;

    private Integer hasGroupImages;

    private Integer hasVedio;

    private Integer hasContent;

    private Integer hasOptions;

    private String createTime;

    private String status;

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

    public String getTempPrefix() {
        return tempPrefix;
    }

    public void setTempPrefix(String tempPrefix) {
        this.tempPrefix = tempPrefix == null ? null : tempPrefix.trim();
    }

    public Integer getHasGroupImages() {
        return hasGroupImages;
    }

    public void setHasGroupImages(Integer hasGroupImages) {
        this.hasGroupImages = hasGroupImages;
    }

    public Integer getHasVedio() {
        return hasVedio;
    }

    public void setHasVedio(Integer hasVedio) {
        this.hasVedio = hasVedio;
    }

    public Integer getHasContent() {
        return hasContent;
    }

    public void setHasContent(Integer hasContent) {
        this.hasContent = hasContent;
    }

    public Integer getHasOptions() {
        return hasOptions;
    }

    public void setHasOptions(Integer hasOptions) {
        this.hasOptions = hasOptions;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}