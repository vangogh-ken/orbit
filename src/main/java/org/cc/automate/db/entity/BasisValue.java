package org.cc.automate.db.entity;

import java.io.Serializable;
import java.util.Date;

import org.cc.automate.utils.StringUtil;


public class BasisValue implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 字符串值(少)
	 */
	private String stringValue;
	/**
	 * 字符串(多)
	 */
	private String textValue;
	/**
	 * 浮点
	 */
	private double doubleValue;
	/**
	 * 整数
	 */
	private int intValue;
	/**
	 * 时间类型值
	 */
	private Date dateValue;
	/**
	 * 实际值
	 */
	@SuppressWarnings("unused")
	private String value;
	/**
	 * 字段
	 */
	private BasisAttribute basisAttribute;
	/**
	 * 实体
	 */
	private BasisSubstance basisSubstance;
	private String descn;
	private String status;
	private Date createTime;
	private Date modifyTime;
	private int displayIndex;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public String getTextValue() {
		return textValue;
	}
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}
	public double getDoubleValue() {
		return doubleValue;
	}
	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}
	public int getIntValue() {
		return intValue;
	}
	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}
	public Date getDateValue() {
		return dateValue;
	}
	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}
	public BasisAttribute getBasisAttribute() {
		return basisAttribute;
	}
	public void setBasisAttribute(BasisAttribute basisAttribute) {
		this.basisAttribute = basisAttribute;
	}
	public BasisSubstance getBasisSubstance() {
		return basisSubstance;
	}
	public void setBasisSubstance(BasisSubstance basisSubstance) {
		this.basisSubstance = basisSubstance;
	}
	public String getDescn() {
		return descn;
	}
	public void setDescn(String descn) {
		this.descn = descn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getDisplayIndex() {
		return displayIndex;
	}
	public void setDisplayIndex(int displayIndex) {
		this.displayIndex = displayIndex;
	}
	public String getValue() {
		String attributeType = this.basisAttribute.getAttributeType();
		if("VARCHAR".equals(attributeType)){
			return this.getStringValue();
		}else if("TEXT".equals(attributeType)){
			return this.getTextValue();
		}else if("INT".equals(attributeType)){
			return String.valueOf(this.getIntValue());
		}else if("DOUBLE".equals(attributeType)){
			return String.valueOf(this.getDoubleValue());
		}else if("DATETIME".equals(attributeType)){
			return StringUtil.parseDateTime(this.getDateValue());
		}else if("TIMESTAMP".equals(attributeType)){
			return StringUtil.parseTimeStamp(this.getDateValue());
		}else{
			return null;
		}
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
