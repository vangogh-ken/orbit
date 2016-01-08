package org.cc.automate.core;

import java.util.Properties;

import org.cc.automate.core.ssh.SSHClient;
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
	
	public static final String USER_ID = "USER_ID";
	public static final String DEFAULT_ENCODING = "UTF-8";
	
	public static boolean isRemoteExecute;
	public static SSHClient deployServerSSHClient;
	public static String deployConfigPath;
	/**
	 * 扫描节点命令路径
	 */
	public static String nodeCommandPath;
	public static String deployCommandPath;
	/**
	 * 配置文件存放路径
	 */
	public static String configTargetSLSPath;
	public static String configTemplatePath_v1;
	static{
		PropertiesFactory propertiesFactory = CP_SpringContext.getBean(PropertiesFactory.class);
		Properties properties = propertiesFactory.getProperties();
		configTargetSLSPath = properties.getProperty("configTargetSLSPath");
		configTemplatePath_v1 = properties.getProperty("configTemplatePath_v1");
		
		deployConfigPath = properties.getProperty("deployServer.configPath");
		
		
		isRemoteExecute = Boolean.valueOf(properties.getProperty("isRemoteExecute"));
		
		String ip = properties.getProperty("deployServer.ip");
		String username = properties.getProperty("deployServer.username");
		String password = properties.getProperty("deployServer.password");
		deployServerSSHClient = new SSHClient(ip, username, password);
	}
	
}
