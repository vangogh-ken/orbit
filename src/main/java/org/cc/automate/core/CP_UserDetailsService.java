package org.cc.automate.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CP_UserDetailsService implements UserDetailsService{
	private static final Logger LOG = LoggerFactory.getLogger(CP_UserDetailsService.class);

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOG.info("CP_UserDetailsService loadUserByUsername");
		//定死权限
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		//定死用户名密码
		String userlongid = "admin";
		Md5PasswordEncoder encode = new Md5PasswordEncoder();
		String password = encode.encodePassword("admin", null);
		
		return new User(userlongid, password, true, true, true, true, authorities);
	}

	
}


