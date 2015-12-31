package org.cc.automate.z.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.cc.automate.config.domain.NodehostDTO;
import org.cc.automate.utils.StringUtil;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JSONTest {
	public StringBuilder content = new StringBuilder();
	
	@Test
	public void t6(){
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			//ArrayNode arrayNode = mapper.createArrayNode();
			//List<Module> ssd = mapper.findModules();
			JsonNode jsonNode = mapper.readTree(new FileInputStream("C:\\Users\\Administrator\\Desktop\\json.txt"));
			for(Iterator<Entry<String, JsonNode>> it = jsonNode.fields(); it.hasNext(); ){
				Entry<String, JsonNode> entry = it.next();
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				NodehostDTO nodehostDTO = mapper.treeToValue(entry.getValue(), NodehostDTO.class);
				//Map<String, Object> map = mapper.treeToValue(entry.getValue(), Map.class);
				System.out.println(1);
			}
			/*for(Iterator<JsonNode> it =  jsonNode.iterator(); it.hasNext();){
				JsonNode node = it.next();
				System.out.println(node.fieldNames().next());
			}*/
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//@Test
	public void t5(){
		String s1 = "A1";
		String s2 = "A2";
		String s3 = "A1";
		String s4 = null;
		
		Set<String> ss = new HashSet<String>();
		ss.add(s1);
		ss.add(s2);
		ss.add(s3);
		ss.add(s4);
		System.out.println(ss.size());
	}
	
	
	//@Test
	public void t4(){
		try {
			String text = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\json.txt"));
			String[] ss = text.split("\r\n");
			StringBuilder content = new StringBuilder();
			for(String s : ss){
				if(s.contains(":")){
					String[] sd = s.split(":");
					for(int i=0, len=sd.length; i<len; i++){
						if(i == 0){
							content.append(StringUtil.replaceBlank(sd[i]));
						}else{
							content.append(":" + sd[i]);
						}
					} 
					content.append("\r\n");
				}else{
					content.append(s + "\r\n");
				}
			}
			System.out.println(content.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//@Test
	public void t3(){
		Yaml yaml = new Yaml();
		yaml.load("");
		
		/*JSONObject jsonObj = JSONObject.fromObject(json);  
        String name = jsonObj.getString("name");  
          
        Iterator it = jsonObj.keys();  
        List<String> keyListstr = new ArrayList<String>();  
        while(it.hasNext()){  
            keyListstr.add(it.next().toString());  
        }  */
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			//ArrayNode arrayNode = mapper.createArrayNode();
			//List<Module> ssd = mapper.findModules();
			JsonNode jsonNode = mapper.readTree(new FileInputStream("C:\\Users\\Administrator\\Desktop\\json.txt"));
			for(Iterator<Entry<String, JsonNode>> it = jsonNode.fields(); it.hasNext(); ){
				Entry<String, JsonNode> entry = it.next();
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				NodehostDTO nodehostDTO = mapper.treeToValue(entry.getValue(), NodehostDTO.class);
				System.out.println(1);
			}
			/*for(Iterator<JsonNode> it =  jsonNode.iterator(); it.hasNext();){
				JsonNode node = it.next();
				System.out.println(node.fieldNames().next());
			}*/
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*try {
			mapper.readValue(new FileInputStream("C:\\Users\\Administrator\\Desktop\\json.txt"), NodehostDTO.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	//@Test
	public void t1(){
		content.append("{\"id\":\"idddd\", \"name\": \"test\"}");
	}
	
	//@Test
	public void t(){
		content.append("{\"id\":\"idddd\", \"name\": \"test\"}");
		System.out.println(content.toString());
		ObjectMapper mapper = new ObjectMapper();
		try {
			TestTeam testTeam = mapper.readValue(content.toString(), TestTeam.class);
			System.out.println(testTeam.getId());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
