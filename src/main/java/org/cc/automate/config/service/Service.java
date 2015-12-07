package org.cc.automate.config.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Service<T> {
	public static Map<String, String> substanceTypeCache = new HashMap<String, String>();
	
	/**
	 * 只获取相关字段信息
	 * @param basisSubstanceTypeId
	 * @param status
	 * @return
	 */
	public Map<String, Object> toAdd(String basisSubstanceTypeId);
	/**
	 * 创建一个新的对象
	 * @param basisSubstanceTypeId
	 * @param status
	 * @param params
	 * @return
	 */
	public boolean doneAdd(String basisSubstanceTypeId, String status, Map<String, String> params);
	
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
	public boolean doneUpdate(String basisSubstanceId, String status, Map<String, String> params);
	
	public List<Map<String, Object>> query(String basisSubstanceTypeId, Map<String, String> params);
	
	public Map<String, Object> getById(String basisSubstanceId);
	
	public boolean deleteById(String basisSubstanceId);
}
