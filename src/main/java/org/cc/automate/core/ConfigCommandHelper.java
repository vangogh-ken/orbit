package org.cc.automate.core;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.cc.automate.core.sh.NodeHostDTO;
import org.cc.automate.utils.ShellUtil;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Administrator
 * 用于脚本执行的处理
 */
@Component
public class ConfigCommandHelper implements ConfigAbstractHelper{
	public List<Map<String, Object>> getNodehosts(){
		List<String> list = ShellUtil.executeSH("");
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			 NodeHostDTO jsonNodeHost = mapper.readValue(list.get(0), NodeHostDTO.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Map<String, Object> getExecuteResult(){
		return null;
	}
}
