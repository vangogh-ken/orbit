package org.cc.automate.db.entity;

import java.io.Serializable;
import java.util.Date;

public class BasisAttribute implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 字段名
	 */
	private String attributeName;
	/**
	 * 字段
	 */
	private String attributeColumn;
	/**
	 * 字段类型
	 */
	private String attributeType;
	/**
	 * 是否必须
	 */
	private String required;
	/**
	 * 是否为可选值
	 */
	private String canSelect;
	private String vAttrId;
	private String vClsId;
	private String vColumn;
	private String vFilterId;
	/**
	 * 类型
	 */
	private BasisSubstanceType basisSubstanceType;
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
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributeColumn() {
		return attributeColumn;
	}
	public void setAttributeColumn(String attributeColumn) {
		this.attributeColumn = attributeColumn;
	}
	public String getAttributeType() {
		return attributeType;
	}
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public String getCanSelect() {
		return canSelect;
	}
	public void setCanSelect(String canSelect) {
		this.canSelect = canSelect;
	}
	public String getvAttrId() {
		return vAttrId;
	}
	public void setvAttrId(String vAttrId) {
		this.vAttrId = vAttrId;
	}
	public String getvClsId() {
		return vClsId;
	}
	public void setvClsId(String vClsId) {
		this.vClsId = vClsId;
	}
	public String getvColumn() {
		return vColumn;
	}
	public void setvColumn(String vColumn) {
		this.vColumn = vColumn;
	}
	public String getvFilterId() {
		return vFilterId;
	}
	public void setvFilterId(String vFilterId) {
		this.vFilterId = vFilterId;
	}
	public BasisSubstanceType getBasisSubstanceType() {
		return basisSubstanceType;
	}
	public void setBasisSubstanceType(BasisSubstanceType basisSubstanceType) {
		this.basisSubstanceType = basisSubstanceType;
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
	
	
}
