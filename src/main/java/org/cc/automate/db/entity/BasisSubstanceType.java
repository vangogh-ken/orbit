package org.cc.automate.db.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ken
 * 基础实体
 */
public class BasisSubstanceType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 实体名称
	 */
	private String typeName;
	/**
	 * 属性
	 */
	private List<BasisAttribute> basisAttributes;
	/**
	 * 创建之前的执行的sqls
	 */
	private String beforeCreateBehaviors;
	/**
	 * 创建之后的执行的sqls
	 */
	private String afterCreateBehaviors;
	/**
	 * 修改之前的执行的sqls
	 */
	private String beforeModifyBehaviors;
	/**
	 * 修改之后的执行的sqls
	 */
	private String afterModifyBehaviors;
	/**
	 * 删除之前的执行的sqls
	 */
	private String beforeDeleteBehaviors;
	/**
	 * 删除之后的执行的sqls
	 */
	private String afterDeleteBehaviors;
	
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
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	public String getBeforeCreateBehaviors() {
		return beforeCreateBehaviors;
	}
	public void setBeforeCreateBehaviors(String beforeCreateBehaviors) {
		this.beforeCreateBehaviors = beforeCreateBehaviors;
	}
	public String getAfterCreateBehaviors() {
		return afterCreateBehaviors;
	}
	public void setAfterCreateBehaviors(String afterCreateBehaviors) {
		this.afterCreateBehaviors = afterCreateBehaviors;
	}
	public String getBeforeModifyBehaviors() {
		return beforeModifyBehaviors;
	}
	public void setBeforeModifyBehaviors(String beforeModifyBehaviors) {
		this.beforeModifyBehaviors = beforeModifyBehaviors;
	}
	public String getAfterModifyBehaviors() {
		return afterModifyBehaviors;
	}
	public void setAfterModifyBehaviors(String afterModifyBehaviors) {
		this.afterModifyBehaviors = afterModifyBehaviors;
	}
	public String getBeforeDeleteBehaviors() {
		return beforeDeleteBehaviors;
	}
	public void setBeforeDeleteBehaviors(String beforeDeleteBehaviors) {
		this.beforeDeleteBehaviors = beforeDeleteBehaviors;
	}
	public String getAfterDeleteBehaviors() {
		return afterDeleteBehaviors;
	}
	public void setAfterDeleteBehaviors(String afterDeleteBehaviors) {
		this.afterDeleteBehaviors = afterDeleteBehaviors;
	}
	public List<BasisAttribute> getBasisAttributes() {
		return basisAttributes;
	}
	public void setBasisAttributes(List<BasisAttribute> basisAttributes) {
		this.basisAttributes = basisAttributes;
	}
	
	
}
