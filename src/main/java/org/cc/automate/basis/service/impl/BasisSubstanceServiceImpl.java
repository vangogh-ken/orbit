package org.cc.automate.basis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cc.automate.basis.service.BasisSubstanceService;
import org.cc.automate.core.BasisSqlHelper;
import org.cc.automate.core.Pager;
import org.cc.automate.db.dao.BasisAttributeDao;
import org.cc.automate.db.dao.BasisSubstanceDao;
import org.cc.automate.db.dao.BasisValueDao;
import org.cc.automate.db.entity.BasisAttribute;
import org.cc.automate.db.entity.BasisSubstance;
import org.cc.automate.db.entity.BasisSubstanceType;
import org.cc.automate.db.entity.BasisValue;
import org.cc.automate.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service("basisSubstanceService")
public class BasisSubstanceServiceImpl implements BasisSubstanceService {
	private Logger logger = LoggerFactory.getLogger(BasisSubstanceServiceImpl.class);
	@Autowired
	private BasisSubstanceDao basisSubstanceDao;
	@Autowired
	private BasisValueDao basisValueDao;
	@Autowired
	private BasisAttributeDao basisAttributeDao;
	@Autowired
	private JdbcTemplate jdcbTemplate;
	@Autowired
	private BasisSqlHelper basisHelper;

	public List<BasisSubstance> getAll() {
		return basisSubstanceDao.getAll();
	}

	public List<BasisSubstance> queryForList(BasisSubstance basisSubstance) {
		return basisSubstanceDao.queryForList(basisSubstance);
	}

	public BasisSubstance queryForOne(BasisSubstance basisSubstance) {
		return basisSubstanceDao.queryForOne(basisSubstance);
	}

	public Pager query(Pager pager, BasisSubstance basisSubstance) {
		List<BasisSubstance> list = basisSubstanceDao.query(pager,
				basisSubstance);
		pager.setResults(list);
		return pager;
	}

	public void add(BasisSubstance basisSubstance) {
		basisSubstanceDao.add(basisSubstance);
	}

	public void delete(String id) {
		basisSubstanceDao.delete(id);
		basisValueDao.deleteByBasisSubstanceId(id);
	}

	public void modify(BasisSubstance basisSubstance) {
		basisSubstanceDao.modify(basisSubstance);
	}

	public BasisSubstance getById(String id) {
		return basisSubstanceDao.getById(id);
	}

	@Override
	public void doBeforeCreateBehaviors(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doAfterCreateBehaviors(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doBeforeModifyBehaviors(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doAfterModifyBehaviors(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doBeforeDeleteBehaviors(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doAfterDeleteBehaviors(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doBehaviors(String id, String sqls) {
		if(!StringUtil.isNullOrEmpty(sqls)){
			
			for(String sql : sqls.split(";")){
				if(sql.contains(":")){
					String[] ts = sql.split("\\s+");
					List<String> params = new ArrayList<String>();
					for(int i=0, len = ts.length; i<len; i++){
						if(ts[i].contains(":")){
							String param = ts[i].split(":")[1];
							params.add(param);
						}
					}
					
					List<Object> values = new ArrayList<Object>();
					for(int i=0, len = params.size(); i<len; i++){
						sql = sql.replace(":" + params.get(i), "?");
						values.add(basisValueDao.getSingleValue(id, params.get(i), false));
					}
					
					jdcbTemplate.update(sql, values.toArray());
					logger.info("doBehaviors Sql:{}, params:{}", sql, values.toArray());
				}else{
					jdcbTemplate.update(sql);
					logger.info("doBehaviors Sql:{}", sql);
				}
			}
		}
		
	}

	@Override
	public String toAddBpm(BasisSubstanceType basisSubstanceType, String status) {
		BasisSubstance basisSubstance = new BasisSubstance();
		String id = StringUtil.getUUID();
		basisSubstance.setId(id);
		basisSubstance.setBasisSubstanceType(basisSubstanceType);
		basisSubstance.setStatus(status);
		basisSubstanceDao.add(basisSubstance);
		
		List<BasisAttribute> basisAttributes = basisAttributeDao.getByBasisSubstanceTypeId(basisSubstanceType.getId());
		for(BasisAttribute basisAttribute : basisAttributes){
			BasisValue basisValue = new BasisValue();
			basisValue.setBasisSubstance(basisSubstance);
			basisValue.setBasisAttribute(basisAttribute);
			basisValueDao.add(basisValue);
		}
		
		return id;
	}

	@Override
	public Map<String, Object> getBasisValueMap(String basisSubstanceId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		BasisSubstance basisSubstance = basisSubstanceDao.getById(basisSubstanceId);
		map.put("basisSubstanceId", basisSubstanceId);
		map.put("status", basisSubstance.getStatus());
		
		List<BasisAttribute> basisAttributes = basisAttributeDao
				.getByBasisSubstanceTypeId(basisSubstance.getBasisSubstanceType().getId());
		List<BasisValue> basisValues = basisValueDao.getByBasisSubstanceId(basisSubstanceId);
		for(BasisAttribute basisAttribute : basisAttributes){
			for(BasisValue basisValue : basisValues){
				if(basisAttribute.getId().equals(basisValue.getBasisAttribute().getId())){
					map.put(basisAttribute.getAttributeColumn(), basisValue.getValue());//使用Column较好理解
					break;
				}
			}
		}
		return map;
	}

	@Override
	public void donePrimeSubstance(String basisSubstanceId, HttpServletRequest request) {
		BasisSubstance basisSubstance = basisSubstanceDao.getById(basisSubstanceId);
		basisSubstance.setModifyTime(new Date());
		basisSubstanceDao.modify(basisSubstance);
		
		List<BasisValue> values = basisValueDao.getByBasisSubstanceId(basisSubstanceId);
		if(values != null && !values.isEmpty()){
			for(BasisValue basisValue : values){
				String value = request.getParameter(basisValue.getId());
				if(!StringUtil.isNullOrEmpty(value)){
					String attributeType = basisValue.getBasisAttribute().getAttributeType();
					if("VARCHAR".equals(attributeType)){
						basisValue.setStringValue(value);
					}else if("TEXT".equals(attributeType)){
						basisValue.setTextValue(value);
					}else if("INT".equals(attributeType)){
						basisValue.setIntValue(Integer.parseInt(value));
					}else if("DOUBLE".equals(attributeType)){
						basisValue.setDoubleValue(Double.parseDouble(value));
					}else if("DATETIME".equals(attributeType) || "TIMESTAMP".equals(attributeType)){
						basisValue.setDateValue(StringUtil.convert(value));
					}
					
					basisValue.setStatus("T");//T表示已填报
					basisValueDao.modify(basisValue);
				}
			}
		}
	}

	@Override
	public Map<String, Object> getBasisValueMapSingle(String basisSubstanceTypeId, String filterText) {
		List<Map<String, Object>> list = getBasisValueMapList(basisSubstanceTypeId, filterText);
		if(list != null && list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getBasisValueMapList(String basisSubstanceTypeId, String filterText) {
		List<Map<String, Object>> list = jdcbTemplate.queryForList(basisHelper.getSqlOfBasisSubstance(20, basisAttributeDao.getByBasisSubstanceTypeId(basisSubstanceTypeId), filterText));
		return list;
	}
}
