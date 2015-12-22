package org.cc.automate.config.service;

import java.util.List;
import java.util.Map;

import org.cc.automate.config.domain.NodeHost;

public interface NodeHostService extends Service<NodeHost> {
	/**
	 * 通过hostname ip判断是否已经扫描过，并且已保存
	 * @return
	 */
	public List<Map<String, Object>> scan();
}
