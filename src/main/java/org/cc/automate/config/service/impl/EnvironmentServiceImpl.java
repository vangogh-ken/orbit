package org.cc.automate.config.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.cc.automate.config.domain.Environment;
import org.cc.automate.config.domain.NodeHost;
import org.cc.automate.config.domain.NodehostDTO;
import org.cc.automate.config.domain.StorageScheme;
import org.cc.automate.config.service.EnvironmentService;
import org.cc.automate.core.BusinessException;
import org.cc.automate.core.ConfigLogicHelper;
import org.cc.automate.core.ConfigTargetHelper;
import org.cc.automate.core.Constant;
import org.cc.automate.core.el.JuelFactory;
import org.cc.automate.core.sh.SHManager;
import org.cc.automate.utils.SpringSecurityUtil;
import org.cc.automate.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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
	public Map<String, Object> initStoragescheme(Map<String, Object> params) {
		if((params.get("GLUSTERFS_NOVA") != null && "T".equals(params.get("GLUSTERFS_NOVA"))) ||
				(params.get("GLUSTERFS_GLANCE") != null && "T".equals(params.get("GLUSTERFS_GLANCE"))) ||
				(params.get("GLUSTERFS_CINDER") != null && "T".equals(params.get("GLUSTERFS_CINDER")))){
			params.put("GLUSTERFS", "T");
		}else{
			params.put("GLUSTERFS", "F");
		}
		
		if((params.get("OCFS2_NOVA") != null && "T".equals(params.get("OCFS2_NOVA"))) ||
				(params.get("OCFS2_GLANCE") != null && "T".equals(params.get("OCFS2_GLANCE"))) ||
				(params.get("OCFS2_CINDER") != null && "T".equals(params.get("OCFS2_CINDER")))){
			params.put("OCFS2", "T");
		}else{
			params.put("OCFS2", "F");
		}
		
		if((params.get("CEPH_NOVA") != null && "T".equals(params.get("CEPH_NOVA"))) ||
				(params.get("CEPH_GLANCE") != null && "T".equals(params.get("CEPH_GLANCE"))) ||
				(params.get("CEPH_CINDER") != null && "T".equals(params.get("CEPH_CINDER")))){
			params.put("CEPH", "T");
		}else{
			params.put("CEPH", "F");
		}
		
		if((params.get("LOCALDISK_NOVA") != null && "T".equals(params.get("LOCALDISK_NOVA")))){
			params.put("LOCALDISK", "T");
		}else{
			params.put("LOCALDISK", "F");
		}
		return params;
	}
	
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("HJPZID", basisSubstanceId);
		List<Map<String, Object>> nodehosts = query(NodeHost.class, params);
		
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
		Map<String, Object> storagescheme = queryForOne(StorageScheme.class, params);
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("HJPZID", basisSubstanceId);
		List<Map<String, Object>> nodehosts = query(NodeHost.class, params);
		Map<String, Object> storagescheme = queryForOne(StorageScheme.class, params);
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("environment", environment);
		variables.put("nodehosts", nodehosts);
		variables.put("storagescheme", storagescheme);
		
		JuelFactory.juelFactory().getValue(Constant.configTemplatePath_v1, Constant.configTargetSLSPath, variables);
		
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

	@Override
	public Map<String, Object> checkName(String basisSubstanceId, String HJMC) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("HJMC", HJMC);
		Map<String, Object> target = queryForOne(Environment.class, params);
		
		Map<String, Object> result = new HashMap<String, Object>();
		if(target == null || target.isEmpty()){
			result.put("result", true);
		}else{
			if(!StringUtil.isNullOrEmpty(basisSubstanceId)){
				if(target.get("ID") != null && basisSubstanceId.equals(target.get("ID"))){
					result.put("result", true);
				}else{
					result.put("result", false);
					result.put("message", "存在重复");
				}
			}else{
				result.put("result", true);
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> nodehosts(String environmentId) {
		Map<String, Object> results = new HashMap<String, Object>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("HJPZID", environmentId);
		List<Map<String, Object>> addedList = queryForList(NodeHost.class, params);
		results.put("addedList", addedList);
		List<Map<String, Object>> addtoList = queryForList(NodeHost.class, null, ("HJPZID != '" + environmentId + "' OR HJPZID IS NULL"));
		results.put("addtoList", addtoList);
		
		results.put("environment", getById(environmentId));
		return results;
	}

	@Override
	public Map<String, Object> addNodehost(String environmentId, String nodehostId) {
		Map<String, Object> nodehost = getById(nodehostId);
		nodehost.put("HJPZID", environmentId);
		boolean flag = doneUpdate(nodehostId, "已添加", nodehost);
		Map<String, Object>  result = new HashMap<String, Object>();
		result.put("result", flag);
		return result;
	}

	@Override
	public Map<String, Object> deleteNodehost(String environmentId, String nodehostId) {
		Map<String, Object> nodehost = getById(nodehostId);
		nodehost.put("HJPZID", "A");//所有将设置为null或空字符串都约定设置为A
		boolean flag = doneUpdate(nodehostId, "已移除", nodehost);
		Map<String, Object>  result = new HashMap<String, Object>();
		result.put("result", flag);
		return result;
	}

	@Override
	public Map<String, Object> getNodehost(String environmentId, String nodehostId) {
		Map<String, Object>  result = new HashMap<String, Object>();
		result.put("environment", getById(environmentId));
		result.put("nodehost", getById(nodehostId));
		return result;
	}

	@Override
	public Map<String, Object> storageschemes(String environmentId) {
		Map<String, Object>  result = new HashMap<String, Object>();
		result.put("environment", getById(environmentId));
		
		Map<String, Object> storagescheme = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("HJPZID", environmentId);
		storagescheme = queryForOne(StorageScheme.class, params);
		if(storagescheme == null){
			doneAdd(StorageScheme.class, "草稿", params);
			result.put("storagescheme", queryForOne(StorageScheme.class, params));
		}else{
			result.put("storagescheme", storagescheme);
		}
		
		result.put("nodehosts", queryForList(NodeHost.class, params));
		return result;
	}

	@Override
	public Map<String, Object> topologies(String environmentId) {
		Map<String, Object>  result = new HashMap<String, Object>();
		result.put("environment", getById(environmentId));
		
		Map<String, Object> storagescheme = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("HJPZID", environmentId);
		storagescheme = queryForOne(StorageScheme.class, params);
		if(storagescheme == null){
			doneAdd(StorageScheme.class, "草稿", params);
			result.put("storagescheme", queryForOne(StorageScheme.class, params));
		}else{
			result.put("storagescheme", storagescheme);
		}
		
		result.put("nodehosts", queryForList(NodeHost.class, params));
		return result;
	}

	@Override
	public Map<String, Object> get(String environmentId) {
		Map<String, Object>  result = new HashMap<String, Object>();
		result.put("environment", getById(environmentId));
		return result;
	}

	@Override
	public Map<String, Object> nodehostsScan(String environmentId) {
		//TODO 数据执行命令进行扫描
		//Map<String, Object> scaned = shManager.executeSH("NODEHOST");
		//String text = (String)scaned.get("result");
		String text = null;
		try {
			text = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\json.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!StringUtil.isNullOrEmpty(text)){
			List<Map<String, Object>> nodehosts = queryAll(NodeHost.class);
			Map<String, Map<String, Object>> nodehostsMap = new HashMap<String, Map<String, Object>>();
			if(nodehosts != null && !nodehosts.isEmpty()){
				for(Map<String, Object> entry : nodehosts){
					nodehostsMap.put((String)entry.get("ZJM"), entry);
				}
			}
			//List<NodehostDTO> nodehostDTOS = StringUtil.JsonFromStr(StringUtil.JsonMarkElement(StringUtil.JsonTrimKey(text)));//通过扫描出来的节点信息
			List<NodehostDTO> nodehostDTOS = StringUtil.JsonFromStr(StringUtil.JsonMarkElement(text));//通过扫描出来的节点信息
			if(nodehostDTOS != null && !nodehostDTOS.isEmpty()){
				for(NodehostDTO nodehostDTO : nodehostDTOS){//TODO 如果新扫描出的节点，有就添加，如果没有跳过.此逻辑还需要进一步明确
					Map<String, Object> entry = nodehostsMap.get(nodehostDTO.getNodename());
					if(entry == null){
						Map<String, Object> nodehost = new HashMap<String, Object>();
						//主机名
						nodehost.put("ZJM", nodehostDTO.getNodename());
						//是否为部署节点
						nodehost.put("SFWBSJD", "autodeploy".equals(nodehostDTO.getRole()) == true ? "T" : "F");
						//管理网卡
						nodehost.put("GLWK", nodehostDTO.getMgnet().get("mgdev"));
						//管理网络IP
						nodehost.put("GLWLIP", nodehostDTO.getMgnet().get("mgip"));
						//可用网卡数据
						Map<String, Object> ipInterfaces = nodehostDTO.getIp_interfaces();
						StringBuilder interfacesText = new StringBuilder();//网卡
						for(Entry<String, Object> item : ipInterfaces.entrySet()){
							//if(StringUtil.isWanaIpInterface(item.getKey()) && !nodehost.get("GLWK").equals(item.getKey())){//此处需要将管理网卡排除开外
							if(StringUtil.isWanaIpInterface(item.getKey())){//此处并不需要将管理网卡排除开外
								interfacesText.append(item.getKey() + ",");
							}
						}
						
						if(interfacesText.length() > 0){
							nodehost.put("KYWK", interfacesText.deleteCharAt(interfacesText.lastIndexOf(",")).toString());
						}
						//IPMI地址
						nodehost.put("IPMIDZ", nodehostDTO.getIpmi_info().get("IP Address"));
						doneAdd(NodeHost.class, "草稿", nodehost);
					}
				}
			}
			
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", true);
		return result;
	}

	
}
