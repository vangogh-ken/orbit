package org.cc.automate.core;

import org.springframework.context.ApplicationContext;

/**
 * 提供Context实例，由此获取一些特殊的Bean
 * @author Administrator
 *
 */
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

	public static Object getBean(String beanName) {
		return CP_SpringContext.getInstance().getApplicationContext().getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> clazz){
		return CP_SpringContext.getInstance().getApplicationContext().getBean(clazz);
	}
}


