package org.cc.automate.config.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cc.automate.config.domain.Environment;
import org.cc.automate.config.domain.NodeHost;
import org.cc.automate.config.domain.StorageScheme;
import org.cc.automate.config.service.EnvironmentService;
import org.cc.automate.core.BusinessException;
import org.cc.automate.core.ConfigLogicHelper;
import org.cc.automate.core.ConfigTargetHelper;
import org.cc.automate.core.el.JuelFactory;
import org.cc.automate.core.sh.SHManager;
import org.cc.automate.utils.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service("environmentService")
public class EnvironmentServiceImpl extends ServiceImpl<Environment> implements EnvironmentService {
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	@Autowired
	private ConfigLogicHelper configLogicHelper;
	@Autowired
	private ConfigTargetHelper configTargetHelper;
	private SHManager shManager = new SHManager();
	
	@Override
	public Map<String, Object> initiate(int version, String basisSubstanceId) {
		if(version == 1){
			return initiateV1(basisSubstanceId);
		}
		return null;
	}

	@Override
	public Map<String, Object> initiateV1(String basisSubstanceId) {
		Map<String, Object> environment = getById(basisSubstanceId);
		Map<String, Object> result = null;
		//校验环境配置
		result = configLogicHelper.checkEnvironmentRequired(environment);
		if((boolean)result.get("result") != true){
			return result;
		}
		
		//校验节点配置
		Map<String, String> params = new HashMap<String, String>();
		params.put("HJPZID", basisSubstanceId);
		List<Map<String, Object>> nodehosts = query(org.cc.automate.config.service.Service.substanceTypeCache.get(NodeHost.class.getName()), params);
		
		if(nodehosts == null || nodehosts.isEmpty()){
			throw new BusinessException("查询不到任何节点配置");
		}
		
		for(Map<String, Object> nodehost : nodehosts){
			result = configLogicHelper.checkNodeHostRequired(environment, nodehost);
			if((boolean)result.get("result") != true){
				return result;
			}
			
			result = configLogicHelper.checkHostNameByRole(nodehost);
			if((boolean)result.get("result") != true){
				return result;
			}
		}
		
		//校验存储配置
		Map<String, Object> storagescheme = queryForOne(org.cc.automate.config.service.Service.substanceTypeCache.get(StorageScheme.class.getName()), params);
		if(storagescheme == null){
			throw new BusinessException("查询不到任何存储配置");
		}
		
		result = configLogicHelper.checkGlusterFS(storagescheme);
		if((boolean)result.get("result") != true){
			return result;
		}
		
		result = configLogicHelper.checkOCFS2(storagescheme);
		if((boolean)result.get("result") != true){
			return result;
		}
		
		result = configTargetHelper.createSLSV1(environment, nodehosts, storagescheme);
		return result;
	}

	@Override
	public Map<String, Object> config(int version, String basisSubstanceId) {
		if(version == 1){
			return configV1(basisSubstanceId);
		}
		return null;
	}

	@Override
	public Map<String, Object> configV1(String basisSubstanceId) {
		Map<String, Object> environment = getById(basisSubstanceId);
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("HJPZID", basisSubstanceId);
		List<Map<String, Object>> nodehosts = query(org.cc.automate.config.service.Service.substanceTypeCache.get(NodeHost.class.getName()), params);
		Map<String, Object> storagescheme = queryForOne(org.cc.automate.config.service.Service.substanceTypeCache.get(StorageScheme.class.getName()), params);
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("environment", environment);
		variables.put("nodehosts", nodehosts);
		variables.put("storagescheme", storagescheme);
		
		JuelFactory.juelFactory().getValue("", "", variables);
		
		result.put("result", true);
		result.put("messgae", null);
		return result;
	}

	@Override
	public Map<String, Object> execute(int version, String basisSubstanceId) {
		if(version == 1){
			 return executeV1(basisSubstanceId);
		}
		return null;
	}

	@Override
	public Map<String, Object> executeV1(String basisSubstanceId) {
		Map<String, Object> environment = getById(basisSubstanceId);
		
		//初始化环境
		boolean flag = true;
		String message = null;
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(!"T".equals(environment.get("SFYTGJY"))){
			flag = false;
			message = "未通过校验不能执行部署";
			result.put("message", message);
			result.put("result", flag);
			return result;
		}
		
		result = shManager.executeSH("ENVIRONMENT");
		messagingTemplate.convertAndSendToUser(SpringSecurityUtil.getCurrentUserName(), "/info", result);
		flag = (boolean)result.get("result");
		//如果有GLUSTERFS
		if(flag && "T".equals(environment.get("GLUSTERFS"))){
			result = shManager.executeSH("GLUSTERFS");
			messagingTemplate.convertAndSendToUser(SpringSecurityUtil.getCurrentUserName(), "/info", result);
			flag = (boolean)result.get("result");
		}
		
		if(flag && "T".equals(environment.get("OCFS2"))){
			result = shManager.executeSH("OCFS2");
			messagingTemplate.convertAndSendToUser(SpringSecurityUtil.getCurrentUserName(), "/info", result);
			flag = (boolean)result.get("result");
		}
		
		if(flag && "T".equals(environment.get("CEPH"))){
			result = shManager.executeSH("CEPH");
			messagingTemplate.convertAndSendToUser(SpringSecurityUtil.getCurrentUserName(), "/info", result);
			flag = (boolean)result.get("result");
		}
		
		if(flag && "T".equals(environment.get("XNHLX_KVM"))){
			result = shManager.executeSH("KVM");
			messagingTemplate.convertAndSendToUser(SpringSecurityUtil.getCurrentUserName(), "/info", result);
			flag = (boolean)result.get("result");
		}
		
		if(flag && "T".equals(environment.get("XNHLX_IRONIC"))){
			result = shManager.executeSH("IRONIC");
			messagingTemplate.convertAndSendToUser(SpringSecurityUtil.getCurrentUserName(), "/info", result);
			flag = (boolean)result.get("result");
		}
		
		if(flag && "T".equals(environment.get("XNHLX_VMWARE"))){
			result = shManager.executeSH("VMWARE");
			messagingTemplate.convertAndSendToUser(SpringSecurityUtil.getCurrentUserName(), "/info", result);
			flag = (boolean)result.get("result");
		}
		
		if(flag){
			result = shManager.executeSH("NEBULA4J");
			messagingTemplate.convertAndSendToUser(SpringSecurityUtil.getCurrentUserName(), "/info", result);
			flag = (boolean)result.get("result");
		}
		return result;
	}
}
