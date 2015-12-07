package org.cc.automate.core;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constant {
	private static Logger LOG = LoggerFactory.getLogger(Constant.class);
	/**
	 * 参数入库时进行转换的分隔符
	 */
	public static final String PARAM_SPLITER = "<!!>";
	
	public static String ipmiLoginUrl = "https://%s/data/login";
	public static String ipmiKvmUrl = "https://%s/viewer.jnlp(%s@0@%s)";
	public static String ipmiMediaUrl = "https://%s/data?type=jnlp&get=vmStart(%s@0@%s)";
	
	public static final String IPMILOGIN = ipmiLoginUrl;
	public static final String IPMIKVM = ipmiKvmUrl;
	public static final String IPMIMEDIA = ipmiMediaUrl;
	public static final String yamlGradeOne = "  ";
	public static final String yamlGradeTow = "    ";
	public static final String yamlGradeThree = "      ";
	public static final String yamlGradeFour = "        ";
	public static final String wrap = "\r\n";
	
	/**
	 * 扫描节点命令路径
	 */
	public static String nodeCommandPath;
	public static String deployCommandPath;
	/**
	 * 配置文件存放路径
	 */
	public static String configSLSFilePath;
	public static String configSLSTemplateV1Path;
	static{
		Properties properties = new Properties();
			try {
				LOG.info("初始化配置文件中");
				properties.load(Constant.class.getResourceAsStream("/config/properties/path.properties"));
				nodeCommandPath = properties.getProperty("nodeCommandPath");
				deployCommandPath = properties.getProperty("deployCommandPath");
				configSLSFilePath = properties.getProperty("configSLSFilePath");
				configSLSTemplateV1Path = properties.getProperty("configSLSTemplateV1Path");
			} catch (IOException e) {
				LOG.info("初始化配置文件出错， {}", e);
				throw new BusinessException("初始化配置文件出错");
			}
	}
	
}
