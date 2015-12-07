package org.cc.automate.core;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/** 
 *Description: <类功能描述>. <br>
 *<p>
	<使用说明>
 </p>
 *Makedate:2014年8月27日 下午5:51:32 
 * @author Administrator  
 * @version V1.0                             
 */
public class CP_SimpleMappingExceptionResolver  extends SimpleMappingExceptionResolver{
	private static final Logger logger = LoggerFactory.getLogger(CP_SimpleMappingExceptionResolver.class);
	@Override
	protected ModelAndView getModelAndView(String viewName, Exception ex,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		logger.error("run time exception:", ex);//将异常信息打印到日志中
		return super.getModelAndView(viewName, ex, request);
	}
}

