package org.cc.automate.basis.service;

import java.util.List;

import org.cc.automate.core.Pager;
import org.cc.automate.db.entity.BasisAttribute;


public interface BasisAttributeService {
	public List<BasisAttribute> getAll();

	public List<BasisAttribute> queryForList(BasisAttribute basisAttribute);

	public BasisAttribute queryForOne(BasisAttribute basisAttribute);

	public Pager query(Pager pager, BasisAttribute basisAttribute);

	public void add(BasisAttribute basisAttribute);

	public void delete(String id);

	public void modify(BasisAttribute basisAttribute);

	public BasisAttribute getById(String id);

	public List<BasisAttribute> getByBasisSubstanceTypeId(String basisSubstanceTypeId);

	/**
	 * 批量添加属性
	 * @param basisAttribute
	 * @param batchCount
	 * @return
	 */
	public boolean doneAddBatch(BasisAttribute basisAttribute, int batchCount, int batchStart, int batchEnd);
}
