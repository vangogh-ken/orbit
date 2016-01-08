package org.cc.automate.core.sh;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.cc.automate.core.CP_SpringContext;
import org.cc.automate.core.Constant;
import org.cc.automate.core.PropertiesFactory;
import org.cc.automate.core.ssh.RemoteExecutor;
import org.cc.automate.utils.StringUtil;
import org.chinacloud.command.CmdDTO;
import org.chinacloud.command.Command;
import org.chinacloud.command.SimpleCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 脚本命令管理
 * @author Administrator
 *
 */
public class SHManager {
	/**
	 * 命令集合
	 */
	public static Map<String, String> shPathStore = new HashMap<String, String>();
	
	private static Logger LOG = LoggerFactory.getLogger(SHManager.class);
	public SHManager(){
		if(shPathStore.isEmpty()){
			PropertiesFactory propertiesFactory = CP_SpringContext.getBean(PropertiesFactory.class);
			Properties properties = propertiesFactory.getProperties();
			for(Entry<Object, Object> entry : properties.entrySet()){
				String key = (String)entry.getKey();
				String value = (String)entry.getValue();
				if(key.startsWith("sh_")){
					shPathStore.put(key.substring(3).toUpperCase(), value);
				}
			}
			LOG.info("SH Command info initiated! command count {}", shPathStore.size());
		}
	}
	
	public Map<String, Object> executeSH(String type){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("type", "DEPLOY");//标记一下类型
		String shPath = shPathStore.get(type);
		if(!StringUtil.isNullOrEmpty(shPath)){
			if(Constant.isRemoteExecute){
				RemoteExecutor remoteExecutor = new RemoteExecutor();
				try {
					Map<String, Object> map = remoteExecutor.execute(Constant.deployServerSSHClient, shPath);
					if((int)map.get("resultCode") == 0){
						result.put("result", true);
						result.put("message", map.get("successText"));
					}else{
						result.put("result", false);
						result.put("message", map.get("errorText"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				SimpleCmd simpleCmd = new SimpleCmd(new CmdDTO(Command.Type.DEFALUT, shPath));
				String text = simpleCmd.execute();//执行脚本返回结果
				result.put("message", text);
				result.put("result", true);
			}
		}
		result.put("COMMAND", type);
		return result;
	}
}
