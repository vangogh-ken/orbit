package org.cc.automate.basis.service.impl;

import java.util.List;

import org.cc.automate.basis.service.BasisAttributeService;
import org.cc.automate.core.Pager;
import org.cc.automate.db.dao.BasisAttributeDao;
import org.cc.automate.db.entity.BasisAttribute;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service("basisAttributeService")
public class BasisAttributeServiceImpl implements BasisAttributeService {
	@Autowired
	private BasisAttributeDao basisAttributeDao;

	public List<BasisAttribute> getAll() {
		return basisAttributeDao.getAll();
	}

	public List<BasisAttribute> queryForList(BasisAttribute basisAttribute) {
		return basisAttributeDao.queryForList(basisAttribute);
	}

	public BasisAttribute queryForOne(BasisAttribute basisAttribute) {
		return basisAttributeDao.queryForOne(basisAttribute);
	}

	public Pager query(Pager pager, BasisAttribute basisAttribute) {
		List<BasisAttribute> list = basisAttributeDao.query(pager,
				basisAttribute);
		pager.setResults(list);
		return pager;
	}

	public void add(BasisAttribute basisAttribute) {
		basisAttributeDao.add(basisAttribute);
	}

	public void delete(String id) {
		basisAttributeDao.delete(id);
	}

	public void modify(BasisAttribute basisAttribute) {
		basisAttributeDao.modify(basisAttribute);
	}

	public BasisAttribute getById(String id) {
		return basisAttributeDao.getById(id);
	}

	@Override
	public List<BasisAttribute> getByBasisSubstanceTypeId(String basisSubstanceTypeId) {
		return basisAttributeDao.getByBasisSubstanceTypeId(basisSubstanceTypeId);
	}

	@Override
	public boolean doneAddBatch(BasisAttribute basisAttribute, int batchCount, int batchStart, int batchEnd) {
		Assert.assertTrue(batchCount > 1 || (batchStart > 0 && batchEnd > 0 && batchStart < batchEnd));
		String attributeName = basisAttribute.getAttributeName();
		String attributeColumn = basisAttribute.getAttributeColumn();
		if(batchEnd == 0 || batchEnd == 0 || batchStart >= batchEnd){
			for(int i=0; i<batchCount; i++){
				basisAttribute.setAttributeName(attributeName + (1 + i));
				basisAttribute.setAttributeColumn(attributeColumn + (1 + i));
				basisAttribute.setDisplayIndex(basisAttribute.getDisplayIndex() + 1);
				basisAttributeDao.add(basisAttribute);
			}
		}else{
			for(int i=batchStart; i < (batchEnd + 1); i++){
				basisAttribute.setAttributeName(attributeName + i);
				basisAttribute.setAttributeColumn(attributeColumn + i);
				basisAttribute.setDisplayIndex(basisAttribute.getDisplayIndex() + 1);
				basisAttributeDao.add(basisAttribute);
			}
		}
		return false;
	}
}
