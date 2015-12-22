package org.cc.automate.config.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.cc.automate.core.BusinessException;
import org.cc.automate.core.Constant;
import org.cc.automate.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/system/")
public class LoginController extends BaseController {
	@Autowired
	private AuthenticationManager myAuthenticationManager;
	
	@RequestMapping(value = "info")
	public String status(){
		return "success";
	}
	
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public Map<String, Object> doLogin(@RequestParam String username, @RequestParam String password,
			HttpServletRequest request){
		if(!StringUtil.isNullOrEmpty(username) && "admin".equals(username) 
				&& !StringUtil.isNullOrEmpty(password) && "admin".equals(password)){
			Map<String, Object> result = new HashMap<String, Object>();
			Authentication authentication = myAuthenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			HttpSession session = request.getSession(true);
			session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
			session.setAttribute(Constant.USER_ID, username);
			
			result.put("result", "success");
			result.put(Constant.USER_ID, username);
			return result;
		}else{
			throw new BusinessException("登录失败");
		}
	}
	
	@RequestMapping(value = "doLogout", method = RequestMethod.POST)
	public Map<String, Object> doLogout(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put(Constant.USER_ID, request.getSession().getAttribute(Constant.USER_ID));
		
		request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");//移除SPRING_SECURITY_CONTEXT
		request.getSession().setMaxInactiveInterval(0);
		request.getSession().invalidate();
		return result;
	}
}
