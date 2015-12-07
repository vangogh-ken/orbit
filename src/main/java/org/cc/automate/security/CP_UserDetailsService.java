package org.cc.automate.security;

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

/** 
 *Description: <类功能描述>. <br>
 *<p>
	<使用说明>
 </p>
 *Makedate:2014年9月4日 下午4:58:33 
 * @author Administrator  
 * @version V1.0                             
 */
public class CP_UserDetailsService implements UserDetailsService{
	private static final Logger logger = LoggerFactory.getLogger(CP_UserDetailsService.class);

	

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		logger.info("CP_UserDetailsService loadUserByUsername");
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		//为了测试方便，用户就写死了，真实环境可以查询数据库
		String userlongid = "admin";
		Md5PasswordEncoder encode = new Md5PasswordEncoder();
		String password = encode.encodePassword("654321", null);
		
		return new User(userlongid, password, true, true,
				true, true, authorities);
	}

	
}


