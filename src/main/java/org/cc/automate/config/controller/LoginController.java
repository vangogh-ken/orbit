package org.cc.automate.config.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@RequestMapping(value = "status")
	public String status(){
		return "success";
	}
	
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public String doLogin(@RequestParam String username, @RequestParam String password,
			HttpServletRequest request){
		if(!StringUtil.isNullOrEmpty(username) && "admin".equals(username) 
				&& !StringUtil.isNullOrEmpty(password) && "admin".equals(password)){
			
			Authentication authentication = myAuthenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			HttpSession session = request.getSession(true);
			session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
			session.setAttribute(Constant.USER_ID, username);
			
			return "success";
		}
		return "error";
	}
}
