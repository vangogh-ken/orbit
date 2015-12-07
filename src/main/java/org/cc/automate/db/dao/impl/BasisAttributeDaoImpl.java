package org.cc.automate.db.dao.impl;

import java.util.List;

import org.cc.automate.db.dao.BasisAttributeDao;
import org.cc.automate.db.entity.BasisAttribute;
import org.springframework.stereotype.Repository;


@Repository("basisAttributeDao")
public class BasisAttributeDaoImpl extends BaseDaoImpl<BasisAttribute>
		implements BasisAttributeDao {

	@Override
	public List<BasisAttribute> getByBasisSubstanceTypeId(
			String basisSubstanceTypeId) {
		return getSqlSession().selectList(
				"basisattribute.getByBasisSubstanceTypeId",
				basisSubstanceTypeId);
	}

	@Override
	public int deleteByBasisSubstanceTypeId(String basisSubstanceTypeId) {
		return getSqlSession().delete(this.getClassName() + ".deleteByBasisSubstanceTypeId", basisSubstanceTypeId);
	}
}
