package org.cc.automate.config.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Service<T> {
	public static Map<Object, String> substanceTypeCache = new HashMap<Object, String>();
	
	/**
	 * 只获取相关字段信息
	 * @param basisSubstanceTypeId
	 * @param status
	 * @return
	 */
	public Map<String, Object> toAdd(Class<?> clazz);
	/**
	 * 创建一个新的对象
	 * @param basisSubstanceTypeId
	 * @param status
	 * @param params
	 * @return
	 */
	public boolean doneAdd(Class<?> clazz, String status, Map<String, Object> params);
	
	/**
	 * 根据对象ID获取本身数据和
	 * @param basisSubstanceId
	 * @return
	 */
	public Map<String, Object> toUpdate(String basisSubstanceId);
	/**
	 * 更新数据
	 * @param basisSubstanceId
	 * @param status
	 * @param params
	 * @return
	 */
	public boolean doneUpdate(String basisSubstanceId, String status, Map<String, Object> params);
	
	public List<Map<String, Object>> query(Class<?> clazz, Map<String, Object> params, String filterText);
	
	public List<Map<String, Object>> query(Class<?> clazz, Map<String, Object> params);
	
	public List<Map<String, Object>> queryForList(Class<?> clazz, Map<String, Object> params, String filterText);
	
	public List<Map<String, Object>> queryForList(Class<?> clazz, Map<String, Object> params);
	
	public Map<String, Object> queryForOne(Class<?> clazz, Map<String, Object> params);
	
	public Map<String, Object> getById(String basisSubstanceId);
	
	public boolean deleteById(String basisSubstanceId);
}
