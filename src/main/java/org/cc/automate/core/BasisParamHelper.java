package org.cc.automate.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

@Component
public class BasisParamHelper {
	public Map<String, String> convertParam(Map<String, String[]> paramMap){
		Map<String, String> map = new HashMap<String, String>();
		for(Entry<String, String[]> entry : paramMap.entrySet()){
			String[] value = entry.getValue();
			if(value.length > 1){
				map.put(entry.getKey(), convertArray(value));
			}else{
				map.put(entry.getKey(), value[0]);
			}
		}
		
		return map;
	}
	
	
	public String convertArray (String[] array){
		StringBuilder text = new StringBuilder();
		for(String str : array){
			text.append(str + Constant.PARAM_SPLITER);
		}
		
		text.substring(0, text.lastIndexOf(Constant.PARAM_SPLITER));
		return text.toString();
	}
}
