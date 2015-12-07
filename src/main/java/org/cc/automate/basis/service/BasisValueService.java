package org.cc.automate.basis.service;

import java.util.List;

import org.cc.automate.core.Pager;
import org.cc.automate.db.entity.BasisValue;


public interface BasisValueService {
	public List<BasisValue> getAll();

	public List<BasisValue> queryForList(BasisValue basisValue);

	public BasisValue queryForOne(BasisValue basisValue);

	public Pager query(Pager pager, BasisValue basisValue);

	public void add(BasisValue basisValue);

	public void delete(String id);

	public void modify(BasisValue basisValue);

	public BasisValue getById(String id);

	/**
	 * 获取单个
	 * 
	 * @param basisSubstanceId
	 * @param idOrColumn
	 * @param isAttributeId
	 * @return
	 */
	public BasisValue getSingleValue(String basisSubstanceId,
			String idOrColumn, boolean isAttributeId);
}
