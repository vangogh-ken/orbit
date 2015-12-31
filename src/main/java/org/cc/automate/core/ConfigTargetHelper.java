package org.cc.automate.core;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.cc.automate.utils.YamlUtil;
import org.springframework.stereotype.Component;



/**
 * @author Administrator
 * 用于对配置文件的处理
 */
@Component
public class ConfigTargetHelper implements ConfigAbstractHelper{
	@SuppressWarnings("unchecked")
	public Map<String, Object> createSLSV1(Map<String, Object> environment, List<Map<String, Object>> nodehosts, Map<String, Object> storagescheme){
		String templatePath = Constant.configTemplatePath_v1;
		Iterable<Object> configSLS = YamlUtil.getMapObject(templatePath);
		for(Iterator<Object> it = configSLS.iterator(); it.hasNext(); ){
			Object target = it.next();
			if(target instanceof Map){
				target = (Map<String, Object>) target;
			}
		}
		
		return null;
	}
}
