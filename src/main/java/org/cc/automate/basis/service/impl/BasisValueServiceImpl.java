package org.cc.automate.basis.service.impl;

import java.util.List;

import org.cc.automate.basis.service.BasisValueService;
import org.cc.automate.core.Pager;
import org.cc.automate.db.dao.BasisValueDao;
import org.cc.automate.db.entity.BasisValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service("basisValueService")
public class BasisValueServiceImpl implements BasisValueService {
	@Autowired
	private BasisValueDao basisValueDao;

	public List<BasisValue> getAll() {
		return basisValueDao.getAll();
	}

	public List<BasisValue> queryForList(BasisValue basisValue) {
		return basisValueDao.queryForList(basisValue);
	}

	public BasisValue queryForOne(BasisValue basisValue) {
		return basisValueDao.queryForOne(basisValue);
	}

	public Pager query(Pager pager, BasisValue basisValue) {
		List<BasisValue> list = basisValueDao.query(pager, basisValue);
		pager.setResults(list);
		return pager;
	}

	public void add(BasisValue basisValue) {
		basisValueDao.add(basisValue);
	}

	public void delete(String id) {
		basisValueDao.delete(id);
	}

	public void modify(BasisValue basisValue) {
		basisValueDao.modify(basisValue);
	}

	public BasisValue getById(String id) {
		return basisValueDao.getById(id);
	}

	@Override
	public BasisValue getSingleValue(String basisSubstanceId,
			String idOrColumn, boolean isAttributeId) {
		return basisValueDao.getSingleValue(basisSubstanceId, idOrColumn, isAttributeId);
	}
}
