package com.esen.youngcms.bean;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fumiao
 * @since 2017-07-28
 */
@TableName("tb_extend_field")
public class ExtendField extends Model<ExtendField> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 扩展模型类型,枚举类型 1.栏目模型 2.广告模型
     */
	private Integer modelType;
    /**
     * 扩展模型主键
     */
	private Integer modelId;
	private String fieldCode;
	private Integer fieldType;
	private String fieldName;
	private String defaultValue;
    /**
     * 必填
     */
	private Integer isMust;
    /**
     * 单独一行
     */
	private Integer isSingleRow;
	private String options;
	private String description;
	private Integer sort;
	private Integer status;
	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public Integer getFieldType() {
		return fieldType;
	}

	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Integer getIsMust() {
		return isMust;
	}

	public void setIsMust(Integer isMust) {
		this.isMust = isMust;
	}

	public Integer getIsSingleRow() {
		return isSingleRow;
	}

	public void setIsSingleRow(Integer isSingleRow) {
		this.isSingleRow = isSingleRow;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getModelType() {
		return modelType;
	}

	public void setModelType(Integer modelType) {
		this.modelType = modelType;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
