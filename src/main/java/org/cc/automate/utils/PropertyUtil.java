package org.cc.automate.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertyUtil {
		public static String xmlDataDir;
		public static String xmlDataName;
		public static String configDir;
		public static String configName;
		public static String templateDir;
		public static String templateName;
		public static String commandPathText;
		public static List<String> commandPathes;
		
		static{
			initProperties();
		}
		
		public static void initProperties(){
			Properties ps = new Properties();
			try {
				ps.load(PropertyUtil.class.getResourceAsStream("/pre-conf.properties"));
				configDir = ps.getProperty("configDir");
				configName = ps.getProperty("configName");
				templateDir = ps.getProperty("templateDir");
				templateName = ps.getProperty("templateName");
				commandPathText = ps.getProperty("commandPathText");
				commandPathes = Arrays.asList(commandPathText.split(","));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			System.out.println(PropertyUtil.templateName);
		}
}

