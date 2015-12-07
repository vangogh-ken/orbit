package org.cc.automate.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author Administrator
 *
 */
public class ConfigUtil {
	private static Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);
	public static void main(String[] args) {
		System.out.println(ip2long("10.111.134.30"));
		System.out.println(long2ip(ip2long("10.111.134.30")));
	}
	
	/**
     * ip地址转成整数.
     * @param ip
     * @return
     */
    public static long ip2long(String ip) {
    	String[] ips = ip.split("[.]");
    	long num =  16777216L*Long.parseLong(ips[0]) + 65536L*Long.parseLong(ips[1]) + 256*Long.parseLong(ips[2]) + Long.parseLong(ips[3]);
    	return num;
    }
    
    /**
     * 整数转成ip地址.
     * @param ipLong
     * @return
     */
    public static String long2ip(long ipLong) {
    	//long ipLong = 1037591503;
    	long mask[] = {0x000000FF,0x0000FF00,0x00FF0000,0xFF000000};
    	long num = 0;
    	StringBuffer ipInfo = new StringBuffer();
    	for(int i=0;i<4;i++){
    		num = (ipLong & mask[i])>>(i*8);
    		if(i>0) ipInfo.insert(0,".");
    		ipInfo.insert(0,Long.toString(num,10));
    	}
    	return ipInfo.toString();
    }

	
	/**
	 * 模板文件数据
	 */
	//public static List<PropertyObject> templateProperties;
	/**
	 * 备份配置
	 */
	/*public static void retrieveConfigFile(){
		try {
			FileUtils.copyFile(new File(PropertyConstant.configDir, PropertyConstant.configName), new File(PropertyConstant.configDir, PropertyConstant.configName.split("\\.")[0] + new Date().getTime() + "." + PropertyConstant.configName.split("\\.")[1]));
			LOG.info("配置文件备份完成");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	/***
	public static void initTemplate(){
		List<PropertyObject> templateProperties = new ArrayList<PropertyObject>();
		try {
			List<String> textList = FileUtils.readLines(new File(PropertyConstant.templateDir, PropertyConstant.templateName), "UTF-8");
			for(String text : textList){
				if(StringUtil.isComment(text)){
					continue;
				}else{
					int grade = StringUtil.getGrade(text);
					PropertyObject primaryProperty = new PropertyObject(grade, StringUtil.getNameEn(text), StringUtil.getNameEn(text), StringUtil.isValued(text), StringUtil.getValue(text));
					templateProperties.add(primaryProperty);
					
					
					int grade = StringUtil.getGrade(text);
					if(grade == 1){
						PropertyObject primaryProperty = new PropertyObject(grade, StringUtil.getNameEn(text), StringUtil.getNameEn(text), StringUtil.isValued(text), StringUtil.getValue(text));
						templateProperties.add(primaryProperty);
						c1Property = primaryProperty;
					}else if(grade == 2){
						PropertyObject nextProperty = new PropertyObject(grade, StringUtil.getNameEn(text), StringUtil.getNameEn(text), StringUtil.isValued(text), StringUtil.getValue(text));
						List<PropertyObject> c1Children = templateProperties.get(templateProperties.size() - 1).getChildProperties();
						if(c1Children == null){
							c1Children = new ArrayList<PropertyObject>();
						}
						c1Children.add(nextProperty);
						templateProperties.get(templateProperties.size() - 1).setChildProperties(c1Children);
					}else if(grade == 3){
						PropertyObject nextProperty = new PropertyObject(grade, StringUtil.getNameEn(text), StringUtil.getNameEn(text), StringUtil.isValued(text), StringUtil.getValue(text));
						List<PropertyObject> c2Children = templateProperties.get(templateProperties.size() - 1).getChildProperties().;
						if(templateProperties.get(templateProperties.size() - 1).getChildProperties().get(templateProperties.get(templateProperties.size() - 1).getChildProperties().size() - 1) == null){
							templateProperties.get(templateProperties.size() - 1).setChildProperties(new ArrayList<PropertyObject>());
						}
						
						templateProperties.get(templateProperties.size() - 1).getChildProperties().add(secondProperty);
					}
				}
			}
			
			LOG.info("模板配置文件初始化完成");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	**/
}
