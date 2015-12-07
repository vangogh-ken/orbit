package org.cc.automate.core;

import org.springframework.context.ApplicationContext;

public class CP_SpringContext {
	private static ApplicationContext applicationContext;

	private static CP_SpringContext springContext;

	private CP_SpringContext() {
	}

	public static CP_SpringContext getInstance() {
		if (springContext == null) {
			springContext = new CP_SpringContext();
		}
		return springContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}

	/**                                                          
	* 描述 : <获得applicationContext中的对象>. <br> 
	*<p> 
		<使用方法说明>  
	 </p>                                                                                                                                                                                                                                                
	* @param beanName
	* @return                                                                                                      
	*/  
	public static Object getBean(String beanName) {
		return CP_SpringContext.getInstance().getApplicationContext().getBean(beanName);
	}
}


