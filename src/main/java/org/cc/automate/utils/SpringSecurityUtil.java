package org.cc.automate.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 从SecurityContextHolder 获取当前用户信息
 */
public class SpringSecurityUtil {

	public static Authentication getAuthentication() {
		SecurityContext context = SecurityContextHolder.getContext();
		return context.getAuthentication();
	}

	public static String getCurrentUserName() {
		Authentication authentication = getAuthentication();
		if ((authentication == null) || (authentication.getPrincipal() == null)) {
			return "";
		}

		return authentication.getName();
	}
}
