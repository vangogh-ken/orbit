package org.cc.automate.db.dao;

import java.util.List;

import org.cc.automate.db.entity.BasisValue;


public interface BasisValueDao extends BaseDao<BasisValue> {
	/**
	 * 
	 * @param basisSubstanceId //对象ID
	 * @param idOrColumn
	 * @param isAttributeId //是否使用属性ID，否的话就使用属性column
	 * @return
	 */
	public BasisValue getSingleValue(String basisSubstanceId, String idOrColumn, boolean isAttributeId);
	/**
	 * 获取所有的值
	 * @param id
	 * @return
	 */
	public List<BasisValue> getByBasisSubstanceId(String basisSubstanceId);
	/**
	 * 删除相应的值
	 * @param id
	 */
	public int deleteByBasisSubstanceId(String basisSubstanceId);
}