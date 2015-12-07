package org.cc.automate.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/system/")
public class TestController extends BaseController {
	@RequestMapping(value = "dashboard")
	public String dashboard(Model model){
		return "dashboard";
	}
}
