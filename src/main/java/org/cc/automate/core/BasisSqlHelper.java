package org.cc.automate.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cc.automate.db.entity.BasisAttribute;
import org.cc.automate.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BasisSqlHelper {
	private static Logger LOG = LoggerFactory.getLogger(BasisSqlHelper.class);
	
	public String getSqlOfBasisSubstanceWithEqual(List<BasisAttribute> basisAttributes, Map<String, String> params){
		StringBuilder filterText = new StringBuilder();
		if(params != null && !params.isEmpty()){
			for(Entry<String, String> entry : params.entrySet()){
				if(StringUtil.isNullOrEmpty(entry.getValue())){
					continue;
				}
				filterText.append(" " + entry.getKey() + " = '" + entry.getValue() + "' AND ");
			}
		}
		
		if(filterText.length() > 0){
			return getSqlOfBasisSubstance(20, basisAttributes, filterText.substring(0, filterText.lastIndexOf(" AND ")));
		}else{
			return getSqlOfBasisSubstance(20, basisAttributes, "");
		}
	}
	
	public String getSqlOfBasisSubstanceWithLike(List<BasisAttribute> basisAttributes, Map<String, String> params){
		StringBuilder filterText = new StringBuilder();
		if(params != null && !params.isEmpty()){
			for(Entry<String, String> entry : params.entrySet()){
				if(StringUtil.isNullOrEmpty(entry.getValue())){
					continue;
				}
				filterText.append(" " + entry.getKey() + " LIKE '%" + entry.getValue() + "%' AND ");
			}
		}
		
		if(filterText.length() > 0){
			return getSqlOfBasisSubstance(20, basisAttributes, filterText.substring(0, filterText.lastIndexOf(" AND ")));
		}else{
			return getSqlOfBasisSubstance(20, basisAttributes, "");
		}
	}
	
	/**
	 * 获取基础数据的查询SQL语句
	 * @return
	 */
	public String getSqlOfBasisSubstance(int limitSize, List<BasisAttribute> basisAttributes, String filterText){
		String sql = null;
		if(basisAttributes.size() > limitSize){
			List<String> sqls =  new ArrayList<String>();
			int len = basisAttributes.size()%limitSize == 0 ? (basisAttributes.size()%limitSize) : ((basisAttributes.size() - basisAttributes.size()%limitSize)/limitSize + 1);
			for(int i=0; i<len; i++){
				sqls.add(getSubSql(basisAttributes.subList(i * limitSize, (i + 1) == len ? basisAttributes.size() - 1  : ((i + 1) * limitSize) - 1)));
			}
			sql = getSumSql(filterText, sqls.toArray(new String[len]));
		}else{
			sql = getSumSql(filterText, getSubSql(basisAttributes));
		}
		
		return sql;
	}
	
	public String getSumSql(String filterText, String... sqls){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM ");
		for(int i=0, len=sqls.length; i<len; i++){
			String subSql = sqls[i];
			sql.append(" (" + subSql + ") AS T" + i);
			if(i == 0){
				sql.append(" LEFT JOIN ");
			}else {
				sql.append(" ON T" + (i - 1) + ".BASIS_SUBSTANCE_ID=T" + i + ".BASIS_SUBSTANCE_ID");
				sql.append(" LEFT JOIN ");
			}
		}
		
		sql.append(" (SELECT ID, STATUS, CREATE_TIME, MODIFY_TIME FROM BASIS_SUBSTANCE) AS SUBSTANCE ON T0.BASIS_SUBSTANCE_ID=SUBSTANCE.ID");
		if(!StringUtil.isNullOrEmpty(filterText)){
			sql.append(" WHERE " + filterText);
		}
		return sql.toString();
	}
	
	
	/**
	 * 分解
	 * @param basisAttributes
	 * @param count
	 * @return
	 */
	public String getSubSql(List<BasisAttribute> basisAttributes){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT " + basisAttributes.get(0).getAttributeColumn() + ".BASIS_SUBSTANCE_ID");
		for(BasisAttribute basisAttribute : basisAttributes){
			sql.append(", " + basisAttribute.getAttributeColumn());
		}
		sql.append(" FROM ");
		String firstColumn = null;//上一个字段
		for(BasisAttribute basisAttribute : basisAttributes){
			sql.append("(SELECT ");
			if("VARCHAR".equals(basisAttribute.getAttributeType())){
				sql.append(" STRING_VALUE AS " + basisAttribute.getAttributeColumn() + ", BASIS_SUBSTANCE_ID ");
			}else if("TEXT".equals(basisAttribute.getAttributeType())){
				sql.append(" TEXT_VALUE AS " + basisAttribute.getAttributeColumn() + ", BASIS_SUBSTANCE_ID ");
			}else if("INT".equals(basisAttribute.getAttributeType())){
				sql.append(" INT_VALUE AS " + basisAttribute.getAttributeColumn() + ", BASIS_SUBSTANCE_ID ");
			}else if("DOUBLE".equals(basisAttribute.getAttributeType())){
				sql.append(" DOUBLE_VALUE AS " + basisAttribute.getAttributeColumn() + ", BASIS_SUBSTANCE_ID ");
			}else if("DATETIME".equals(basisAttribute.getAttributeType()) || "TIMESTAMP".equals(basisAttribute.getAttributeType())){
				sql.append(" DATE_VALUE AS " + basisAttribute.getAttributeColumn() + ", BASIS_SUBSTANCE_ID ");
			}else{
				if(LOG.isDebugEnabled()){
					LOG.info("错误的数据类型 : {}", basisAttribute.getAttributeType());
				}
				return null;
			}
			
			sql.append(" FROM BASIS_VALUE WHERE BASIS_ATTR_ID='" + basisAttribute.getId() + "')");
			sql.append(" AS " + basisAttribute.getAttributeColumn());
			
			if(firstColumn == null){
				firstColumn = basisAttribute.getAttributeColumn();
				sql.append(" LEFT JOIN ");
			}else{
				sql.append(" ON " + firstColumn + ".BASIS_SUBSTANCE_ID=" + basisAttribute.getAttributeColumn() + ".BASIS_SUBSTANCE_ID");
				sql.append(" LEFT JOIN ");
			}
		}
		
		sql.delete(sql.lastIndexOf(" LEFT JOIN "), sql.length());
		return sql.toString();
	}
}
