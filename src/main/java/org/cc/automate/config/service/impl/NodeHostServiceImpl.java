package org.cc.automate.config.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cc.automate.config.domain.NodeHost;
import org.cc.automate.config.service.NodeHostService;
import org.cc.automate.core.ConfigCommandHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("nodeHostService")
public class NodeHostServiceImpl extends ServiceImpl<NodeHost> implements NodeHostService {
	@Autowired
	private ConfigCommandHelper configCommandHelper;
	@Override
	public List<Map<String, Object>> scan() {
		List<Map<String, Object>> nodehosts = new ArrayList<Map<String, Object>>();
		Map<String, Object> params = new HashMap<String, Object>();
		for(Map<String, Object> item : configCommandHelper.getNodehosts()){
			params.put("ZJM", item.get("ZJM"));
			params.put("GLWLIP", item.get("GLWLIP"));
			
			Map<String, Object> map = queryForOne(NodeHost.class, params);
			if(map != null){
				continue;
			}else{
				
				
				nodehosts.add(item);
			}
		}
		
		return nodehosts;
	}
}
