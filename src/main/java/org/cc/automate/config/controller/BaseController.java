package org.cc.automate.config.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cc.automate.core.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class BaseController {
	private static Logger LOG = LoggerFactory.getLogger(BaseController.class);
	@Resource
	private MessageSource messageSource;
	
	@ExceptionHandler
	@ResponseBody
	public Map<String, Object> exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		int status = HttpServletResponse.SC_BAD_REQUEST;
		int errorCode = HttpServletResponse.SC_BAD_REQUEST;
		String message = "";
		// 业务异常
		if (ex instanceof BusinessException) {
			status = ((BusinessException) ex).getStatus();
			errorCode = ((BusinessException) ex).getErrorCode();
			message = messageSource.getMessage(String.valueOf(((BusinessException) ex).getErrorCode()), null, ((BusinessException) ex).getMessage(), request.getLocale());

		} else if (ex instanceof MissingServletRequestParameterException) {
			// springmvc 参数错误异常
			errorCode = HttpServletResponse.SC_NOT_FOUND;
			message = ex.getMessage();
		} else {
			// 其他系统异常
			LOG.error(ex.getMessage(), ex);
			status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			errorCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			message = messageSource.getMessage("500", null, "server error", request.getLocale());
		}
		response.setStatus(status);
		map.put("status", status);
		map.put("errorCode", errorCode);
		map.put("message", message);

		return map;
	}
}
