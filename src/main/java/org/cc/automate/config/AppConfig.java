package org.cc.automate.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "org.cc", 
	excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class })})
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableTransactionManagement
public class AppConfig {
	
}
