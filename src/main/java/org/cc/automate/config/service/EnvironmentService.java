package org.cc.automate.config.service;

import java.util.Map;

import org.cc.automate.config.domain.Environment;

public interface EnvironmentService extends Service<Environment> {
	/**
	 * 初始化最终选择的存储结果
	 * @param params
	 * @return
	 */
	public Map<String, Object> initStoragescheme(Map<String, Object> params);
	/**
	 * 校验逻辑
	 * @param basisSubstanceId
	 * @return
	 */
	public Map<String, Object> initiate(int version, String basisSubstanceId);
	public Map<String, Object> initiateV1(String basisSubstanceId);
	
	/**
	 * 生成配置
	 * @param version
	 * @param basisSubstanceId
	 * @return
	 */
	public Map<String, Object> config(int version, String basisSubstanceId);
	public Map<String, Object> configV1(String basisSubstanceId);
	
	/**
	 * 执行脚本
	 * @param version
	 * @param basisSubstanceId
	 * @return
	 */
	public Map<String, Object> execute(int version, String basisSubstanceId);
	public Map<String, Object> executeV1(String basisSubstanceId);
	/**
	 * 检查环境名称是否重复
	 * @param basisSubstanceId
	 * @param HJMC
	 * @return
	 */
	public Map<String, Object> checkName(String basisSubstanceId, String HJMC);
	/**
	 * 获取环境相关节点信息
	 * @param environmentId
	 * @return
	 */
	public Map<String, Object> nodehosts(String environmentId);
	
	/**
	 * 获取节点信息
	 * @param environmentId
	 * @param nodehostId
	 * @return
	 */
	public Map<String, Object> getNodehost(String environmentId, String nodehostId);
	
	/**
	 * 添加节点
	 * @param environmentId
	 * @param nodehostId
	 * @return
	 */
	public Map<String, Object> addNodehost(String environmentId, String nodehostId);
	/**
	 * 删除节点
	 * @param environmentId
	 * @param nodehostId
	 * @return
	 */
	public Map<String, Object> deleteNodehost(String environmentId, String nodehostId);
	/**
	 * 获取存储方案
	 * @param environmentId
	 * @return
	 */
	public Map<String, Object> storageschemes(String environmentId);
	/**
	 * 获取环境的所有信息
	 * @param basisSubstanceId
	 * @return
	 */
	public Map<String, Object> topologies(String environmentId);
	
	public Map<String, Object> get(String environmentId);
	/**
	 * 扫描节点信息
	 * @param environmentId
	 * @return
	 */
	public Map<String, Object> nodehostsScan(String environmentId);
	
	
}
