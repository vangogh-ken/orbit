package org.cc.automate.config.controller;

import org.cc.automate.basis.service.BasisSubstanceTypeService;
import org.cc.automate.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/system/")
public class LoginController extends BaseController {
	@Autowired
	private BasisSubstanceTypeService basisSubstanceTypeService;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "toLogin")
	public String toLogin(){
		return "success";
	}
	
	@RequestMapping(value = "doneLogin")
	public String doLogin(String userName, String password){
		if(!StringUtil.isNullOrEmpty(userName) && "admin".equals(userName) 
				&& !StringUtil.isNullOrEmpty(password) && "admin".equals(password)){
			return "success";
		}
		return "error";
	}
}
