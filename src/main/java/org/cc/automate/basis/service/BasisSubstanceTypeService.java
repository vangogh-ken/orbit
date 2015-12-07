package org.cc.automate.basis.service;

import java.util.List;
import java.util.Map;

import org.cc.automate.core.Pager;
import org.cc.automate.db.entity.BasisAttribute;
import org.cc.automate.db.entity.BasisSubstanceType;


public interface BasisSubstanceTypeService {
	public List<BasisSubstanceType> getAll();

	public List<BasisSubstanceType> queryForList(BasisSubstanceType basisSubstanceType);

	public BasisSubstanceType queryForOne(BasisSubstanceType basisSubstanceType);

	public Pager query(Pager pager, BasisSubstanceType basisSubstanceType);

	public void add(BasisSubstanceType basisSubstanceType);

	public void delete(String id);

	public void modify(BasisSubstanceType basisSubstanceType);

	public BasisSubstanceType getById(String id);
	
	public Map<String, Object> toAddAttribute(String basisSubstanceTypeId);
	public boolean doneAddAttribute(String basisSubstanceTypeId, BasisAttribute basisAttribute);
}
