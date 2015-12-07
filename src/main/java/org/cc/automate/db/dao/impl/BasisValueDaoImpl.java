package org.cc.automate.db.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cc.automate.db.dao.BasisValueDao;
import org.cc.automate.db.entity.BasisValue;
import org.springframework.stereotype.Repository;


@Repository("basisValueDao")
public class BasisValueDaoImpl extends BaseDaoImpl<BasisValue> implements
		BasisValueDao {

	@Override
	public BasisValue getSingleValue(String basisSubstanceId, String idOrColumn, boolean isAttributeId) {
		if(isAttributeId){
			Map<String, String> map = new HashMap<String, String>();
			map.put("basisSubstanceId", basisSubstanceId);
			map.put("basisAttributeId", idOrColumn);
			return getSqlSession().selectOne("basisvalue.getByBasisAttributeId", map);
		}else{
			Map<String, String> map = new HashMap<String, String>();
			map.put("basisSubstanceId", basisSubstanceId);
			map.put("basisAttributeColumn", idOrColumn);
			return getSqlSession().selectOne("basisvalue.getByBasisAttributeColumn", map);
		}
	}

	@Override
	public List<BasisValue> getByBasisSubstanceId(String basisSubstanceId) {
		return getSqlSession().selectList("basisvalue.getByBasisSubstanceId", basisSubstanceId);
	}

	@Override
	public int deleteByBasisSubstanceId(String basisSubstanceId) {
		return getSqlSession().delete("basisvalue.deleteByBasisSubstanceId", basisSubstanceId);
	}

}
