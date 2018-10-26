package com.esen.youngcms.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName(value="tb_advert",resultMap="BaseResultMap")
public class Advert extends Model<Advert>{
	
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	@TableId(type=IdType.AUTO)
    private Integer id;
	
    private Integer clickNum;

    private String createTime;

    private String endTime;

    private String imgUrl;

    private String startTime;

    private String title;

    private String url;

    private Integer advertPositionId;
    
    private String advertPositionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

	public Integer getAdvertPositionId() {
		return advertPositionId;
	}

	public void setAdvertPositionId(Integer advertPositionId) {
		this.advertPositionId = advertPositionId;
	}

	public String getAdvertPositionName() {
		return advertPositionName;
	}

	public void setAdvertPositionName(String advertPositionName) {
		this.advertPositionName = advertPositionName;
	}

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}