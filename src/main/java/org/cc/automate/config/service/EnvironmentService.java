package org.cc.automate.config.service;

import java.util.Map;

import org.cc.automate.config.domain.Environment;

public interface EnvironmentService extends Service<Environment> {
	/**
	 * 校验逻辑并生成文件
	 * @param basisSubstanceId
	 * @return
	 */
	public Map<String, Object> initiate(int version, String basisSubstanceId);
	public Map<String, Object> initiateV1(String basisSubstanceId);
	
	public Map<String, Object> config(int version, String basisSubstanceId);
	public Map<String, Object> configV1(String basisSubstanceId);
}
