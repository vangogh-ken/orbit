package org.cc.automate.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.cc.automate.core.CP_InitializingInterceptor;
import org.cc.automate.core.CP_PropertyEditorRegistrar;
import org.cc.automate.core.CP_SimpleMappingExceptionResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.cc", useDefaultFilters = false, 
	includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})
})
public class MvcConfig extends WebMvcConfigurationSupport {
	
	private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);
	
	
    @Bean
    public ViewResolver viewResolver() {
    	logger.info("ViewResolver");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/content/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    /**                                                          
    * 描述 : <注册消息资源处理器>. <br> 
    *<p> 
    	<使用方法说明>  
     </p>                                                                                                                                                                                                                                                
    * @return                                                                                                      
    */  
    @Bean
    public MessageSource messageSource() {
    	logger.info("MessageSource");
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    	messageSource.setBasename("config.messages.messages");
    	
    	return messageSource;
    }
    
    /**                                                          
    * 描述 : <注册servlet适配器>. <br> 
    *<p> 
    	<只需要在自定义的servlet上用@Controller("映射路径")标注即可>  
     </p>                                                                                                                                                                                                                                                
    * @return                                                                                                      
    */  
    @Bean
    public HandlerAdapter servletHandlerAdapter(){
    	logger.info("HandlerAdapter");
    	return new SimpleServletHandlerAdapter();
    }
    
    /**                                                          
    * 描述 : <本地化拦截器>. <br> 
    *<p> 
    	<使用方法说明>  
     </p>                                                                                                                                                                                                                                                
    * @return                                                                                                      
    */  
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
    	logger.info("LocaleChangeInterceptor");
    	return new LocaleChangeInterceptor();
    }
    
    /**                                                          
    * 描述 : <基于cookie的本地化资源处理器>. <br> 
    *<p> 
    	<使用方法说明>  
     </p>                                                                                                                                                                                                                                                
    * @return                                                                                                      
    */  
    @Bean(name="localeResolver")
    public CookieLocaleResolver cookieLocaleResolver(){
    	logger.info("CookieLocaleResolver");
    	return new CookieLocaleResolver();
    }
    
    /**                                                          
    * 描述 : <注册自定义拦截器>. <br> 
    *<p> 
    	<使用方法说明>  
     </p>                                                                                                                                                                                                                                                
    * @return                                                                                                      
    */  
    @Bean
    public CP_InitializingInterceptor initializingInterceptor(){
    	logger.info("CP_InitializingInterceptor");
    	return new CP_InitializingInterceptor();
    }
    
    
    /**                                                          
     * 描述 : <RequestMappingHandlerMapping需要显示声明，否则不能注册自定义的拦截器>. <br> 
     *<p> 
     	<这个比较奇怪,理论上应该是不需要的>  
      </p>                                                                                                                                                                                                                                                
     * @return                                                                                                      
     */ 
    @Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
    	logger.info("RequestMappingHandlerMapping");
		return super.requestMappingHandlerMapping();
	}

    /**                                                          
    * 描述 : <添加拦截器>. <br> 
    *<p> 
    	<使用方法说明>  
     </p>                                                                                                                                                                                                                                                
    * @param registry                                                                                                      
    */  
    @Override
	protected void addInterceptors(InterceptorRegistry registry) {
    	logger.info("addInterceptors start");
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(initializingInterceptor());
		logger.info("addInterceptors end");
	}

    /**                                                          
     * 描述 : <HandlerMapping需要显示声明，否则不能注册资源访问处理器>. <br> 
     *<p> 
     	<这个比较奇怪,理论上应该是不需要的>  
      </p>                                                                                                                                                                                                                                                
     * @return                                                                                                      
     */ 
    @Bean
	public HandlerMapping resourceHandlerMapping() {
    	logger.info("HandlerMapping");
    	return super.resourceHandlerMapping();
    }
    
    /**                                                          
     * 描述 : <资源访问处理器>. <br> 
     *<p> 
     	<可以在jsp中使用/static/**的方式访问/WEB-INF/static/下的内容>  
      </p>                                                                                                                                                                                                                                                
     * @param registry                                                                                                      
     */  
	@Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.info("addResourceHandlers");
        registry.addResourceHandler("/s2/**").addResourceLocations("/s2/");
        //registry.addResourceHandler("/icon/").addResourceLocations("icon");
    }
	
	@Override
	protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
    
	/**                                                          
	* 描述 : <文件上传处理器>. <br> 
	*<p> 
		<使用方法说明>  
	 </p>                                                                                                                                                                                                                                                
	* @return                                                                                                      
	*/  
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver(){
		logger.info("CommonsMultipartResolver");
		return new CommonsMultipartResolver();
	}
	
	/**                                                          
	* 描述 : <异常处理器>. <br> 
	*<p> 
		<系统运行时遇到指定的异常将会跳转到指定的页面>  
	 </p>                                                                                                                                                                                                                                                
	* @return                                                                                                      
	*/  
	@Bean(name="exceptionResolver")
	public CP_SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
		logger.info("CP_SimpleMappingExceptionResolver");
		CP_SimpleMappingExceptionResolver simpleMappingExceptionResolver= new CP_SimpleMappingExceptionResolver();
		simpleMappingExceptionResolver.setDefaultErrorView("common_error");
		simpleMappingExceptionResolver.setExceptionAttribute("exception");
		Properties properties = new Properties();
		properties.setProperty("java.lang.RuntimeException", "common_error");
		simpleMappingExceptionResolver.setExceptionMappings(properties);
		return simpleMappingExceptionResolver;
	}
	
	 /**                                                          
     * 描述 : <RequestMappingHandlerAdapter需要显示声明，否则不能注册通用属性编辑器>. <br> 
     *<p> 
     	<这个比较奇怪,理论上应该是不需要的>  
      </p>                                                                                                                                                                                                                                                
     * @return                                                                                                      
     */ 
	
	/**
     * 为映射请求处理器配置消息转换器
     * @return
     */
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
        requestMappingHandlerAdapter.setMessageConverters(createMessageConverters());
        return requestMappingHandlerAdapter;
    }
	
	/**                                                          
	* 描述 : <注册通用属性编辑器>. <br> 
	*<p> 
		<这里只增加了字符串转日期和字符串两边去空格的处理>  
	 </p>                                                                                                                                                                                                                                                
	* @return                                                                                                      
	*/  
	@Override
	protected ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {
		logger.info("ConfigurableWebBindingInitializer");
		ConfigurableWebBindingInitializer initializer = super.getConfigurableWebBindingInitializer();
		CP_PropertyEditorRegistrar register = new CP_PropertyEditorRegistrar();
		register.setFormat("yyyy-MM-dd");
		initializer.setPropertyEditorRegistrar(register);
		return initializer;
	}
	
    private List<HttpMessageConverter<?>> createMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        // 字节数组消息转换器
        converters.add(new ByteArrayHttpMessageConverter());
         
        // 文本消息转换器
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setWriteAcceptCharset(false);
         
        // 文本消息转换器所支持的文本类型
        ArrayList<MediaType> textTypes = new ArrayList<MediaType>();
        // 原生格式
        textTypes.add(MediaType.TEXT_PLAIN);
        // HTML格式
        textTypes.add(MediaType.TEXT_HTML);
        // 以us-ascii编码XML内容
        textTypes.add(MediaType.TEXT_XML);
        // 以指定字符集编码XML内容
        textTypes.add(MediaType.APPLICATION_XML);
         
        stringConverter.setSupportedMediaTypes(textTypes);
        converters.add(stringConverter);
         
        // XML消息转换器
        //converters.add(new XmlAwareFormHttpMessageConverter());
        converters.add(new AllEncompassingFormHttpMessageConverter());
        converters.add(new Jaxb2RootElementHttpMessageConverter());
         
        //Json消息转换器
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(JSONBinder.createMapper());
        converters.add(jsonConverter);
         
        return converters;
    }
}

