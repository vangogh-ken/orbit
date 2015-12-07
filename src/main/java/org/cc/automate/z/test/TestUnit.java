package org.cc.automate.z.test;

import org.cc.automate.config.service.EnvironmentService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestUnit {
	private final static Logger LOG = LoggerFactory.getLogger(TestUnit.class);
	
	public ApplicationContext applicationContext = null;
	
	@Test
	public void test(){
		applicationContext = new AnnotationConfigApplicationContext("org.cc");
		System.out.println(applicationContext);
		System.out.println(applicationContext.getBean(JdbcTemplate.class));
	}
	
	@Test
	public void t1(){
		EnvironmentService environmentService = (EnvironmentService)applicationContext.getBean("environmentService");
		environmentService.configV1("d2ca581c-c436-4fd2-bc7f-cf6084d88937");
	}
}
