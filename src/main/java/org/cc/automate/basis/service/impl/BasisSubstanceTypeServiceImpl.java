package org.cc.automate.basis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cc.automate.basis.service.BasisSubstanceTypeService;
import org.cc.automate.core.Pager;
import org.cc.automate.db.dao.BasisAttributeDao;
import org.cc.automate.db.dao.BasisSubstanceTypeDao;
import org.cc.automate.db.entity.BasisAttribute;
import org.cc.automate.db.entity.BasisSubstanceType;
import org.cc.automate.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("basisSubstanceTypeService")
public class BasisSubstanceTypeServiceImpl implements BasisSubstanceTypeService {
	@Autowired
	private BasisSubstanceTypeDao basisSubstanceTypeDao;
	@Autowired
	private BasisAttributeDao basisAttributeDao;

	public List<BasisSubstanceType> getAll() {
		return basisSubstanceTypeDao.getAll();
	}

	public List<BasisSubstanceType> queryForList(BasisSubstanceType basisSubstanceType) {
		return basisSubstanceTypeDao.queryForList(basisSubstanceType);
	}

	public BasisSubstanceType queryForOne(BasisSubstanceType basisSubstanceType) {
		return basisSubstanceTypeDao.queryForOne(basisSubstanceType);
	}

	public Pager query(Pager pager, BasisSubstanceType basisSubstanceType) {
		List<BasisSubstanceType> list = basisSubstanceTypeDao.query(pager, basisSubstanceType);
		pager.setResults(list);
		return pager;
	}

	public void add(BasisSubstanceType basisSubstanceType) {
		basisSubstanceTypeDao.add(basisSubstanceType);
	}

	public void delete(String id) {
		basisSubstanceTypeDao.delete(id);
		basisAttributeDao.deleteByBasisSubstanceTypeId(id);
	}

	public void modify(BasisSubstanceType basisSubstanceType) {
		basisSubstanceTypeDao.modify(basisSubstanceType);
	}

	public BasisSubstanceType getById(String id) {
		return basisSubstanceTypeDao.getById(id);
	}


	@Override
	public Map<String, Object> toAddAttribute(String basisSubstanceTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hasAddData", basisAttributeDao.getByBasisSubstanceTypeId(basisSubstanceTypeId));
		return map;
	}

	@Override
	public boolean doneAddAttribute(String basisSubstanceTypeId, BasisAttribute basisAttribute) {
		basisAttribute.setBasisSubstanceType(basisSubstanceTypeDao.getById(basisSubstanceTypeId));
		boolean flag = false;
		if(StringUtil.isNullOrEmpty(basisAttribute.getId())){
			flag = basisAttributeDao.add(basisAttribute) == 1 ? true : false;
		}else{
			flag = basisAttributeDao.modify(basisAttribute) == 1 ? true : false;
		}
		return flag;
	}
}
