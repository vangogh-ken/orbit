package org.cc.automate.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cc.automate.utils.StringUtil;
import org.springframework.stereotype.Component;

/**
 * 环境配置的校验
 * 节点配置的校验
 * 存储方案的校验
 * @author Administrator 配置逻辑校验工具
 */
@Component
public class ConfigLogicHelper implements ConfigAbstractHelper{
	public boolean checkLogic() {
		return false;
	}
	
	/**
	 * 所选虚拟类型至少选一种
	 * 所选NOVAR存储至少选一种
	 * 所选CINDER存储至少选一种
	 * 所选GLANCE存储只能选择一种
	 * @param environmentObject
	 * @return
	 */
	public Map<String, Object> checkEnvironmentRequired(Map<String, Object> environmentObject){
		
		boolean result = true;
		String message = "";
		
		int countVirtual = 0;
		int countNova = 0;//所选NOVAR存储至少选一种
		int countCinder = 0;//所选CINDER存储至少选一种
		int countGlance = 0;//所选GLANCE存储只能选择一种
		for(Entry<String, Object> entry : environmentObject.entrySet()){
			if(entry.getKey().startsWith("XNHLX_") && !StringUtil.isNullOrEmpty((String)entry.getValue())){
				countVirtual++;
			}else if(entry.getKey().endsWith("_NOVA") && !StringUtil.isNullOrEmpty((String)entry.getValue())){
				countNova++;
			}else if(entry.getKey().endsWith("_CINDER") && !StringUtil.isNullOrEmpty((String)entry.getValue())){
				countCinder++;
			}else if(entry.getKey().endsWith("_GLANCE") && !StringUtil.isNullOrEmpty((String)entry.getValue())){
				countGlance++;
			}else{
				if(entry.getValue() == null || StringUtil.isNullOrEmpty(String.valueOf(entry.getValue()))){
					result = false;
					message = entry.getKey() + "不能为空";
				}
			}
		}
		
		if(result){
			if(countVirtual == 0){
				result = false;
				message = "至少选择一种虚拟化";
			}else if(countNova == 0){
				result = false;
				message = "至少选择一种Nova存储方案";
			}else if(countCinder == 0){
				result = false;
				message = "至少选择一种Cinder存储方案";
			}else if(countGlance != 1){
				result = false;
				message = "只能选择一种Glance存储方案";
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("message", message);
		
		return map;
	}
	
	/**
	 * 根据环境配置数据校验节点信息配置：
	 * 虚拟化类型是环境配置中已选择的
	 * 管理网段与存储网段为环境配置中所填
	 * @param environmentObject
	 * @param nodehostObject
	 * @return
	 */
	public Map<String, Object> checkNodeHostRequired(Map<String, Object> environmentObject, Map<String, Object> nodehostObject){
		String message = null;
		boolean result = true;
		if(!StringUtil.isNullOrEmpty((String)environmentObject.get("ID")) &&
				!StringUtil.isNullOrEmpty((String)nodehostObject.get("HJPZID")) && 
				environmentObject.get("ID").equals(nodehostObject.get("HJPZID"))){
			
			for(Entry<String, Object> entry : nodehostObject.entrySet()){
				if("JDJS".equals(entry.getKey()) || "XNHLX".equals(entry.getKey()) || "NOVA_CC".equals(entry.getKey())){
					if(StringUtil.isNullOrEmpty((String)entry.getValue())){
						result = false;
						message = entry.getKey() + "不能为空";
					}
				}else if("CCWK".equals(entry.getKey()) || "CCWLIP".equals(entry.getKey()) || "CCWLYM".equals(entry.getKey())){
					if(!environmentObject.get("GLWD_S").equals(environmentObject.get("GLWD_Z")) || !environmentObject.get("CCWD_S").equals(environmentObject.get("CCWD_Z"))){
						if(StringUtil.isNullOrEmpty((String)entry.getValue())){
							result = false;
							message = entry.getKey() + "不能为空";
						}
					}
					
				}else if("VM_HOST".equals(entry.getKey()) || "VM_USER".equals(entry.getKey()) || "VM_PASSWORD".equals(entry.getKey()) || "VM_CLUSTER".equals(entry.getKey())){
					if(nodehostObject.get("XNHLX").equals("VMWARE")){
						if(StringUtil.isNullOrEmpty((String)entry.getValue())){
							result = false;
							message = entry.getKey() + "不能为空";
						}
					}
				}
				
				if(StringUtil.isNullOrEmpty((String)entry.getValue())){
					result = false;
					message = entry.getKey() + "不能为空";
				}
			}
			
		}else{
			result = false;
			message = "节点配置与环境配置不匹配";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("message", message);
		return map;
	}
	/**
	 * 
	 * 与Servers数量为GlusterFS的数据份数的倍数
	 * @param storageSchemeObject
	 * @return
	 */
	public Map<String, Object> checkGlusterFS(Map<String, Object> storageSchemeObject) {
		boolean result = true;
		if ("GlusterFS".equals(storageSchemeObject.get("FALX"))
				&& !StringUtil.isNullOrEmpty((String) storageSchemeObject.get("FWZJ"))) {
			String[] servers = ((String) storageSchemeObject.get("FWZJ")).split(Constant.PARAM_SPLITER);
			int c = Integer.parseInt((String) storageSchemeObject.get("SJFS"));
			if (c == 0) {
				result = true;
			} else if (c == 2 && servers.length % 2 == 0) {
				result = true;
			} else if (c == 3 && servers.length % 2 == 0) {
				result = true;
			} else {
				result = false;
			}
		} else {
			result = false;
		}
		
		String message = result == true ? null : "server数应为数据份数的倍数";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("message", message);
		
		return map;
	}
	
	/**
	 * 校验OCFS2,CINDER与NOVA和GALNCE不能一样，NOVA和GLANCE可以一样，IQN地址或者共享磁盘ID至少2个，至多3个
	 * @param storageSchemeObject
	 * @return
	 */
	public Map<String, Object> checkOCFS2(Map<String, Object> storageSchemeObject){
		boolean result = true;
		String message = null;
		if ("OCFS2".equals(storageSchemeObject.get("FALX"))
				&& !StringUtil.isNullOrEmpty((String) storageSchemeObject.get("SFYGZ"))) {
			if("T".equals(StringUtil.isNullOrEmpty((String) storageSchemeObject.get("SFYGZ")))){
				List<String> list = new ArrayList<String>();
				list.add((String) storageSchemeObject.get("NOVA_GXCPID"));
				list.add((String) storageSchemeObject.get("GLANCE_GXCPID"));
				if(list.contains((String) storageSchemeObject.get("CINDER_GXCPID"))){
					result = false;
					message = "CINDER共享磁盘ID不能与NOVA或GLANCE一致";
				}
			}else{
				String[] iqns = ((String) storageSchemeObject.get("LJDYIQN")).split(Constant.PARAM_SPLITER);
				String[] adds = ((String) storageSchemeObject.get("LJDYDZ")).split(Constant.PARAM_SPLITER);
				List<String> list = new ArrayList<String>();
				for(String iqn : iqns){
					if(list.contains(iqn)){
						result = false;
						message = "IQN地址填写重复";
					}else{
						list.add(iqn);
					}
				}
				
				for(String add : adds){
					if(list.contains(add)){
						result = false;
						message = "服务地址填写重复";
					}else{
						list.add(add);
					}
				}
				
				if(iqns.length < 2 || iqns.length > 3 || adds.length < 2 || adds.length > 3 || iqns.length != adds.length){
					result = false;
					message = "服务器地址与IQN地址数量必须一致，且只能为2或3";
				}else{
					if(iqns.length == 2 && adds.length == 2 && 
							(int)storageSchemeObject.get("GLANCE_LJDY") == (int)storageSchemeObject.get("NOVA_LJDY") &&
							(int)storageSchemeObject.get("GLANCE_LJDY") != (int)storageSchemeObject.get("CINDER_LJDY")){
						
					}else if(iqns.length == 3 && adds.length == 3 && 
							(int)storageSchemeObject.get("GLANCE_LJDY") != (int)storageSchemeObject.get("NOVA_LJDY") &&
							(int)storageSchemeObject.get("GLANCE_LJDY") != (int)storageSchemeObject.get("CINDER_LJDY") &&
							(int)storageSchemeObject.get("NOVA_LJDY") != (int)storageSchemeObject.get("CINDER_LJDY")){
						
					}else{
						result = false;
						message = "CINDER挂载的存储与GLANCE和NOVA不能一致，若挂载数量为3，则应相互不一致";
					}
				}
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("message", message);
		
		return map;
	}
	

	
	/**
	 * 角色名与主机名的校验
	 * @param nodehostObject
	 * @return
	 */
	public Map<String, Object> checkHostNameByRole(Map<String, Object> nodehostObject){
		return checkHostNameByRole((String)nodehostObject.get("JDJS"), (String)nodehostObject.get("ZJM"));
	}
	public Map<String, Object> checkHostNameByRole(String roleName, String hostName){
		boolean result = true;
		
		if("cc".equals(roleName)){
			result =  hostName.startsWith("cc") && hostName.matches("*\\.*\\.*");
		}else if("nn".equals(roleName)){
			result =  hostName.startsWith("nn") && hostName.matches("*\\.*\\.*");
		}else if("nc".equals(roleName)){
			result =  hostName.startsWith("nc") && hostName.matches("*\\.*\\.*");
		}else{
			result = false; 
		}
		
		String message = result == true ? null : "节点角色与节点名称不匹配";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("message", message);
		
		return map;
	}

	/**
	 * 
	 * ip是0.0.0.0到255.255.255.255。其中数字不能是012之类0开头。 
	 * [1-9]?\\d 表示0-99 
	 * 1\\d{2} 表示100-199 
	 * 2[0-4]\\d 表示200-249 
	 * 25[0-5] 表示250-255
	 * |符号分开的表达式只要一个满足就不看后面的表达式了，你的问题就是\\d|[1-9]\\d|1\\d{2}这里就是第一个\\d就满足了。
	 * 
	 * 
	 * String s = "^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$";
	 * @return
	 */
	public boolean checkIpArea(String text) {
		String pattern = "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}";
		return text.matches(pattern);
	}
	
	public boolean checkIp(String text){
		return text.matches("(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}");
	}
	
	public boolean checkIpArea(String textS, String textE){
		return false;
	}
	
	
	public static void main(String[] args) {
		System.out.println(new ConfigLogicHelper().checkHostNameByRole("cc", "cc.d.faf"));
		//System.out.println(String.valueOf(0));
	}
	//如果Glance存储只能选择了一种存储方案，Cinder可以选择多种
}
