package org.cc.automate.config.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cc.automate.config.domain.Environment;
import org.cc.automate.config.domain.NodeHost;
import org.cc.automate.config.domain.StorageScheme;
import org.cc.automate.config.service.Service;
import org.cc.automate.core.BasisSqlHelper;
import org.cc.automate.core.BusinessException;
import org.cc.automate.db.dao.BasisAttributeDao;
import org.cc.automate.db.dao.BasisSubstanceDao;
import org.cc.automate.db.dao.BasisSubstanceTypeDao;
import org.cc.automate.db.dao.BasisValueDao;
import org.cc.automate.db.entity.BasisAttribute;
import org.cc.automate.db.entity.BasisSubstance;
import org.cc.automate.db.entity.BasisSubstanceType;
import org.cc.automate.db.entity.BasisValue;
import org.cc.automate.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class ServiceImpl<T> {
	private static Logger LOG = LoggerFactory.getLogger(ServiceImpl.class);
	String basisSubstanceTypeId;
	@Autowired
	private BasisSqlHelper basisSqlHelper;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private BasisSubstanceTypeDao basisSubstanceTypeDao;
	@Autowired
	private BasisAttributeDao basisAttributeDao;
	@Autowired
	private BasisSubstanceDao basisSubstanceDao;
	@Autowired
	private BasisValueDao basisValueDao;
	
	static{
		Service.substanceTypeCache.put(Environment.class.getName(), "5b959b67-91b1-11e5-bf87-10604b6fd31b");
		Service.substanceTypeCache.put(NodeHost.class.getName(), "6fe2b956-91b1-11e5-bf87-10604b6fd31b");
		Service.substanceTypeCache.put(StorageScheme.class.getName(), "74ef1aed-91b1-11e5-bf87-10604b6fd31b");
	}
	
	public Map<String, Object> toAdd(String basisSubstanceTypeId){
		Map<String, Object> map = new HashMap<String, Object>();
		BasisSubstanceType basisSubstanceType = basisSubstanceTypeDao.getById(basisSubstanceTypeId);
		map.put("basisAttributes", basisAttributeDao.getByBasisSubstanceTypeId(basisSubstanceType.getId()));
		return map;
	}
	
	public boolean doneAdd(String basisSubstanceTypeId, String status, Map<String, String> params){
		BasisSubstanceType basisSubstanceType = basisSubstanceTypeDao.getById(basisSubstanceTypeId);
		
		BasisSubstance basisSubstance = new BasisSubstance();
		String basisSubstanceId = StringUtil.getUUID();
		basisSubstance.setId(basisSubstanceId);
		basisSubstance.setBasisSubstanceType(basisSubstanceType);
		basisSubstance.setStatus(status);
		basisSubstanceDao.add(basisSubstance);
		
		List<BasisValue> list = new ArrayList<BasisValue>();
		List<BasisAttribute> basisAttributes = basisAttributeDao.getByBasisSubstanceTypeId(basisSubstanceType.getId());
		for(BasisAttribute basisAttribute : basisAttributes){
			BasisValue basisValue = new BasisValue();
			basisValue.setBasisSubstance(basisSubstance);
			basisValue.setBasisAttribute(basisAttribute);
			list.add(basisValue);
		}
		
		basisValueDao.insertBatch(list);
		
		return doneUpdate(basisSubstanceId, status, params);
	}
	
	
	public Map<String, Object> toUpdate(String basisSubstanceId){
		return getById(basisSubstanceId);
	}
	
	public boolean doneUpdate(String basisSubstanceId, String status, Map<String, String> params){
		BasisSubstance basisSubstance = basisSubstanceDao.getById(basisSubstanceId);
		if(basisSubstance == null){
			throw new BusinessException("查询不到任何相关记录");
		}
		
		basisSubstance.setModifyTime(new Date());
		basisSubstance.setStatus(status);
		basisSubstanceDao.modify(basisSubstance);
		
		List<BasisValue> values = basisValueDao.getByBasisSubstanceId(basisSubstanceId);
		if(values != null && !values.isEmpty()){
			for(BasisValue basisValue : values){
				String value = params.get(basisValue.getBasisAttribute().getAttributeColumn());
				if(!StringUtil.isNullOrEmpty(value)){
					String attributeType = basisValue.getBasisAttribute().getAttributeType();
					switch (attributeType){
						case "VARCHAR":
							basisValue.setStringValue(value);
							break;
						
						case "TEXT":
							basisValue.setTextValue(value);
							break;
						
						case "INT":
							basisValue.setIntValue(Integer.parseInt(value));
							break;
						
						case "DOUBLE":
							basisValue.setDoubleValue(Double.parseDouble(value));
							break;
						
						case "DATETIME":
							basisValue.setDateValue(StringUtil.convert(value));
							break;
							
						case "TIMESTAMP":
							basisValue.setDateValue(StringUtil.convert(value));
							break;
					}
					/*
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
					}*/
					
					basisValue.setStatus("T");//T表示已填报
					//basisValueDao.modify(basisValue);
				}
			}
			basisValueDao.updateBatch(values);
		}
		
		return true;
	}
	
	public boolean deleteById(String basisSubstanceId){
		if(basisValueDao.deleteByBasisSubstanceId(basisSubstanceId) > 0
				&& basisSubstanceDao.delete(basisSubstanceId) == 1){
			return true;
		}
		return false;
	}
	
	public List<Map<String, Object>> query(String basisSubstanceTypeId, Map<String, String> params){
		String sql = basisSqlHelper.getSqlOfBasisSubstanceWithLike(basisAttributeDao.getByBasisSubstanceTypeId(basisSubstanceTypeId), params);
		LOG.info("Executing sql : {} ", sql);
		return jdbcTemplate.queryForList(sql);
	}
	
	public Map<String, Object> queryForOne(String basisSubstanceTypeId, Map<String, String> params){
		String sql = basisSqlHelper.getSqlOfBasisSubstanceWithEqual(basisAttributeDao.getByBasisSubstanceTypeId(basisSubstanceTypeId), params);
		LOG.info("Executing sql : {} ", sql);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if(list == null || list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
	
	public Map<String, Object> getById(String basisSubstanceId){
		BasisSubstance basisSubstance = basisSubstanceDao.getById(basisSubstanceId);
		if(basisSubstance == null){
			throw new BusinessException("查询不到任何相关记录");
		}
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("ID", basisSubstanceId);
		String sql = basisSqlHelper.getSqlOfBasisSubstanceWithEqual(
				basisAttributeDao.getByBasisSubstanceTypeId(basisSubstance.getBasisSubstanceType().getId()), params);
		LOG.info("Executing sql : {} ", sql);
		
		try{
			return jdbcTemplate.queryForMap(sql);
		}catch (EmptyResultDataAccessException e){
			LOG.info("Could not find any data!");
			throw new BusinessException("查询不到任何相关记录");
		}
	}
}