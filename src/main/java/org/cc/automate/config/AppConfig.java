package org.cc.automate.config;

import org.cc.automate.core.PropertiesFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "org.cc", 
	excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class })})
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableTransactionManagement
@PropertySource(value = "classpath:/config/properties/db.properties", ignoreResourceNotFound=false)
public class AppConfig {
	@Bean
	public PropertiesFactory propertiesFactory(){
		return new PropertiesFactory();
	}
	
	@Bean  
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() { 
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setProperties(propertiesFactory().getProperties());
	    return configurer;  
	} 
}
