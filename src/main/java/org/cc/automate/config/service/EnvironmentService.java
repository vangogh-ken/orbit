package org.cc.automate.config.service;

import java.util.Map;

import org.cc.automate.config.domain.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

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
	
}
