package org.cc.automate.basis.controller;


import java.util.Map;

import org.cc.automate.basis.service.BasisSubstanceTypeService;
import org.cc.automate.core.Pager;
import org.cc.automate.db.entity.BasisAttribute;
import org.cc.automate.db.entity.BasisSubstanceType;
import org.cc.automate.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/basis/")
public class BasisSubstanceTypeController {
	@Autowired
	private BasisSubstanceTypeService basisSubstanceTypeService;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "basis-substance-type-list")
	public String unread(Model model, BasisSubstanceType basisSubstanceType, @ModelAttribute Pager pager) {
		if (pager == null) {
			pager = new Pager(1);
		}
		pager = basisSubstanceTypeService.query(pager, basisSubstanceType);

		model.addAttribute("pager", pager);
		return "/basis/basis-substance-type-list";
	}

	@RequestMapping(value = "basis-substance-type-input")
	public String input(Model model, String id) {
		BasisSubstanceType item = null;
		if (id != null) {
			item = basisSubstanceTypeService.getById(id);
		}
		model.addAttribute("item", item);
		return "/basis/basis-substance-type-input";
	}

	@RequestMapping(value = "basis-substance-type-save", method = RequestMethod.POST)
	public String add(BasisSubstanceType basisSubstanceType, RedirectAttributes redirectAttributes) {
		if(basisSubstanceType.getId() == null){
			basisSubstanceTypeService.add(basisSubstanceType);
		}else{
			basisSubstanceTypeService.modify(basisSubstanceType);
		}
		return "redirect:basis-substance-type-list";
	}
	
	@RequestMapping(value = "basis-substance-type-remove")
	public String remove(Model model, String[] selectedItem, RedirectAttributes redirectAttributes) {
		for (String id : selectedItem) {
			basisSubstanceTypeService.delete(id);
		}
		return "redirect:basis-substance-type-list";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "basis-attribute-to-add-attribute")
	public Map<String, Object> toAddAttribute(@RequestParam(value="basisSubstanceTypeId", required=true)String basisSubstanceTypeId){
		return basisSubstanceTypeService.toAddAttribute(basisSubstanceTypeId);
	}
	
	@ResponseBody
	@RequestMapping(value = "basis-attribute-done-add-attribute")
	public String doneAddAttribute(@RequestBody BasisAttribute basisAttribute, 
			@RequestParam(value="basisSubstanceTypeId",required=true)String basisSubstanceTypeId,
			String basisAttributeId){
		if(!StringUtil.isNullOrEmpty(basisAttributeId)){
			basisAttribute.setId(basisAttributeId);
		}
		
		if(basisSubstanceTypeService.doneAddAttribute(basisSubstanceTypeId, basisAttribute)){
			return "success";
		}else{
			return "error";
		}
		
	}
}
