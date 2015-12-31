package org.cc.automate.core;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.cc.automate.config.domain.NodehostDTO;
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
			 NodehostDTO jsonNodeHost = mapper.readValue(list.get(0), NodehostDTO.class);
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
	
	public String nodehostsScan(){
		//暂且使用写死数据
		String text = null;
		try {
			text = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\json.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return text;
	}
}
