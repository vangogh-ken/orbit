package org.cc.automate.db.dao;

import java.util.List;
import java.util.Map;

import org.cc.automate.db.entity.BasisAttribute;


public interface BasisAttributeDao extends BaseDao<BasisAttribute> {
	/**
	 * 获取所有字段
	 * @param id
	 * @return
	 */
	public List<BasisAttribute> getByBasisSubstanceTypeId(String basisSubstanceTypeId);
	
	public Map<String, BasisAttribute> getByBasisSubstanceTypeIdWithColumnMap(String basisSubstanceTypeId);
	
	public int deleteByBasisSubstanceTypeId(String basisSubstanceTypeId);
}