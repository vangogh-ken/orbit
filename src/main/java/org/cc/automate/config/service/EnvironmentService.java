package org.cc.automate.config.service;

import java.util.Map;

import org.cc.automate.config.domain.Environment;

public interface EnvironmentService extends Service<Environment> {
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
	
}
