package org.cc.automate.db.dao;

import java.util.List;

import org.cc.automate.db.entity.BasisAttribute;


public interface BasisAttributeDao extends BaseDao<BasisAttribute> {
	/**
	 * 获取所有字段
	 * @param id
	 * @return
	 */
	public List<BasisAttribute> getByBasisSubstanceTypeId(String basisSubstanceTypeId);
	
	public int deleteByBasisSubstanceTypeId(String basisSubstanceTypeId);
}