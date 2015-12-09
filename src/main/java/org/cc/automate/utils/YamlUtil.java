package org.cc.automate.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.cc.automate.core.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Node;

public class YamlUtil {
	private static Logger LOG = LoggerFactory.getLogger(YamlUtil.class);
	
	public static void main(String[] args) {
		try {
			System.out.println(FileUtils.readFileToString(new File("C:\\T\\config_template_v1.sls"), "UTF-8"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Iterable<Object> configSLS = YamlUtil.getMapObject("C:\\T\\config_template_v1.sls");
		Yaml yaml = new Yaml();
		Node node = yaml.represent(configSLS);
		try {
			yaml.dump(configSLS, new FileWriter(new File("C:\\T\\t.sls")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Iterator<Object> it = configSLS.iterator(); it.hasNext(); ){
			Object target = it.next();
			if(target instanceof Map){
				Map<String, Object> targetMap = (Map<String, Object>) target;
				for(Entry<String, Object> entry : targetMap.entrySet()){
					System.out.println(entry.getKey());
				}
			}else if(target instanceof List){
				
			}
		}
	}
	
	public static void tt(){
		
	}
	
	public static void t(){
		StringBuilder text = new StringBuilder();
		text.append("mg_nw:" + Constant.wrap);
		text.append(Constant.yamlGradeOne + "hosts:" + Constant.wrap);
	}
	
	public static Iterable<Object> getMapObject(String path){
		Yaml yaml = new Yaml();
		try {
			return yaml.loadAll(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			if(LOG.isDebugEnabled()){
				LOG.error("文件 {} 不符合YAML格式，错误：  {}", new Object[]{path, e.getMessage()});
			}
		}
		
		return null;
	}
	
	public static <T> T getMapObject(String path, Class<T> clazz){
		Yaml yaml = new Yaml();
		try {
			return yaml.loadAs(new FileInputStream(new File(path)), clazz);
		} catch (FileNotFoundException e) {
			if(LOG.isDebugEnabled()){
				LOG.error("文件 {} 类型 {} 不符合YAML格式，错误：  {}", new Object[]{path, clazz.getName(), e.getMessage()});
			}
		}
		
		return null;
	}
	
	public static boolean toStore(Object yamlFormedObject){
		return false;
	}
	
	/*	public static void main(String[] args) {
		initAttributeType();
	}
	public static List<AttributeType> initAttributeType(){
		Yaml yaml = new Yaml();
		try {
			Iterable load = yaml.loadAll(new FileInputStream(new File(PropertyUtil.templateDir, PropertyUtil.templateName)));
			for (Iterator<Map<String, Object>> it = load.iterator(); it
					.hasNext();) {
				List<AttributeType> list = toType(0, "config", it.next());
				for(AttributeType t : list){
					System.out.println(t.getNameEn() + " : " + t.getPriority() + " : " + t.getValueType());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static List<AttributeInstance> initInstance() {
		Yaml yaml = new Yaml();
		try {
			Iterable load = yaml.loadAll(new FileInputStream(new File(PropertyUtil.configDir, PropertyUtil.configName)));
			for (Iterator<Map<String, Object>> it = load.iterator(); it
					.hasNext();) {
				List<AttributeInstance> list = toInstance("configuration",
						it.next());
				for (AttributeInstance i : list) {
					System.out.println(i.getDescn() + " : " + i.getValue());
				}
				return list;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static List<AttributeInstance> toInstance(String key, Object object) {
		List<AttributeInstance> attributeInstances = new ArrayList<AttributeInstance>();
		if (object instanceof HashMap) {
			Map<String, Object> map = (HashMap) object;
			for (String k : map.keySet()) {
				attributeInstances.addAll(toInstance(k, map.get(k)));
			}
		} else if (object instanceof ArrayList) {
			List<Object> list = (ArrayList) object;
			for (Object o : list) {
				attributeInstances.addAll(toInstance(key, o));
			}
		} else {
			AttributeInstance instance = new AttributeInstance();
			instance.setValue(object == null ? "" : object.toString());
			instance.setDescn(key);
			if (object instanceof String) {
			} else if (object instanceof Double) {

			} else if (object instanceof Integer) {

			} else if (object instanceof Long) {

			} else if (object instanceof Boolean) {

			} else {
				System.out.println("暂不能识别的属性 ： " + object.getClass() + " : "
						+ object.toString());
			}

			attributeInstances.add(instance);
		}

		return attributeInstances;
	}
	
	
	public static List<AttributeType> toType(int priority, String key, Object object) {
		List<AttributeType> attributeTypes = new ArrayList<AttributeType>();
		AttributeType t = new AttributeType();
		t.setNameEn(key);
		t.setPriority(priority);
		attributeTypes.add(t);
		if (object instanceof HashMap) {
			Map<String, Object> map = (HashMap) object;
			for (String k : map.keySet()) {
				attributeTypes.addAll(toType(priority + 1, k, map.get(k)));
			}
		} else if (object instanceof ArrayList) {
			List<Object> list = (ArrayList) object;
			
			attributeTypes.addAll(toType(priority + 1, key, list.get(0)));
			for (Object o : list) {
				attributeTypes.addAll(toType(priority + 1, key, o));
			}
		} else {
			AttributeType attributeType = new AttributeType();
			attributeType.setNameEn(key);
			attributeType.setPriority(priority);
			
			if (object instanceof String) {
				t.setValueType(String.class.getSimpleName());
			} else if (object instanceof Double) {
				t.setValueType(Double.class.getSimpleName());
			} else if (object instanceof Integer) {
				t.setValueType(Integer.class.getSimpleName());
			} else if (object instanceof Long) {
				t.setValueType(Long.class.getSimpleName());
			} else if (object instanceof Boolean) {
				t.setValueType(Boolean.class.getSimpleName());
			} else {
				System.out.println("暂不能识别的属性 ： " + object.getClass() + " : " + object.toString());
			}

			//attributeTypes.add(t);
		}
		return attributeTypes;
	}*/
	
}
