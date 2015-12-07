package org.cc.automate.basis.controller;


import org.cc.automate.basis.service.BasisValueService;
import org.cc.automate.core.Pager;
import org.cc.automate.db.entity.BasisValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/basis/")
public class BasisValueController {
	@Autowired
	private BasisValueService basisValueService;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "basis-value-list")
	public String unread(Model model, BasisValue basisValue, @ModelAttribute Pager pager) {
		if (pager == null) {
			pager = new Pager(1);
		}
		pager = basisValueService.query(pager, basisValue);

		model.addAttribute("pageView", pager);
		return "/basis/basis-value-list";
	}

	@RequestMapping(value = "basis-value-input")
	public String input(Model model, String id) {
		BasisValue item = null;
		if (id != null) {
			item = basisValueService.getById(id);
		}
		model.addAttribute("item", item);
		return "/basis/basis-value-input";
	}

	@RequestMapping(value = "basis-value-save", method = RequestMethod.POST)
	public String add(BasisValue basisValue, RedirectAttributes redirectAttributes) {
		if(basisValue.getId() == null){
			basisValueService.add(basisValue);
		}else{
			basisValueService.modify(basisValue);
		}
		return "redirect:basis-value-list";
	}
	
	@RequestMapping(value = "basis-value-remove")
	public String remove(Model model, String[] selectedItem, RedirectAttributes redirectAttributes) {
		for (String id : selectedItem) {
			basisValueService.delete(id);
		}
		return "redirect:basis-value-list.do";
	}
}
