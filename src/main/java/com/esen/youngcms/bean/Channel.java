package com.esen.youngcms.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName(value="tb_channel",resultMap="BaseResultMap")
public class Channel extends Model<Channel>{
	
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	@TableId(type=IdType.AUTO)
    private Integer id;

    private String name;

    private Integer pId;

    private String code;

    private String sort;

    private Integer isNav;
    
    @TableField(exist=false)
    private String pName;

    private Boolean isParent;
    
    private Boolean open;
    
    private String imgUrl;
    
    private String template;
    
    private Integer isOut;
    
    private String outUrl;
    
    private Integer width;
    
    private Integer height;
    
    private String mateTitle;
    
    private String mateKeywords;
    
    private String mateDescription;
    
    private Integer flowId;
    
    @TableField(exist=false)
    private List<Channel> children=new ArrayList<Channel>();
    
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

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public Integer getIsNav() {
        return isNav;
    }

    public void setIsNav(Integer isNav) {
        this.isNav = isNav;
    }

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Integer getIsOut() {
		return isOut;
	}

	public void setIsOut(Integer isOut) {
		this.isOut = isOut;
	}

	public String getOutUrl() {
		return outUrl;
	}

	public void setOutUrl(String outUrl) {
		this.outUrl = outUrl;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getMateTitle() {
		return mateTitle;
	}

	public void setMateTitle(String mateTitle) {
		this.mateTitle = mateTitle;
	}

	public String getMateKeywords() {
		return mateKeywords;
	}

	public void setMateKeywords(String mateKeywords) {
		this.mateKeywords = mateKeywords;
	}

	public String getMateDescription() {
		return mateDescription;
	}

	public void setMateDescription(String mateDescription) {
		this.mateDescription = mateDescription;
	}

	public Integer getFlowId() {
		return flowId;
	}

	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}

	public List<Channel> getChildren() {
		return children;
	}

	public void setChildren(List<Channel> children) {
		this.children = children;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}