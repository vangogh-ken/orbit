package org.cc.automate.basis.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cc.automate.basis.service.BasisSubstanceService;
import org.cc.automate.core.Pager;
import org.cc.automate.db.entity.BasisSubstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "/basis/")
public class BasisSubstanceController {
	@Autowired
	private BasisSubstanceService basisSubstanceService;

	@RequestMapping(value = "basis-substance-list")
	public String unread(Model model, BasisSubstance basisSubstance, @ModelAttribute Pager pager) {
		if (pager == null) {
			pager = new Pager(1);
		}
		pager = basisSubstanceService.query(pager, basisSubstance);

		model.addAttribute("pageView", pager);
		return "/basis/basis-substance-list";
	}

	@RequestMapping(value = "basis-substance-input")
	public String input(Model model, String id) {
		BasisSubstance item = null;
		if (id != null) {
			item = basisSubstanceService.getById(id);
		}
		model.addAttribute("item", item);
		return "/basis/basis-substance-input";
	}

	@RequestMapping(value = "basis-substance-save", method = RequestMethod.POST)
	public String add(BasisSubstance basisSubstance, RedirectAttributes redirectAttributes) {
		if(basisSubstance.getId() == null){
			basisSubstanceService.add(basisSubstance);
		}else{
			basisSubstanceService.modify(basisSubstance);
		}
		return "redirect:basis-substance-list";
	}
	
	@RequestMapping(value = "basis-substance-remove")
	public String remove(Model model, String[] selectedItem, RedirectAttributes redirectAttributes) {
		for (String id : selectedItem) {
			basisSubstanceService.delete(id);
		}
		return "redirect:basis-substance-list";
	}
	
	@ResponseBody
	@RequestMapping(value = "basis-substance-done-prime-substance")
	public String donePrimeSubstance(@RequestParam(value="basisSubstanceId",required=true)String basisSubstanceId, 
			HttpServletRequest request){
		basisSubstanceService.donePrimeSubstance(basisSubstanceId, request);
		return "success";
	}
	
	/**
	 * 根据过滤条件获取对应类型的数据
	 * @param basisSubstanceId
	 * @param filterText
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "basis-substance-get-value-substance")
	public Map<String, Object> getSingleValueMap(@RequestParam(value="basisSubstanceId",required=true)String basisSubstanceId, 
			@RequestParam(value="filterText",required=true)String filterText){
		return basisSubstanceService
				.getBasisValueMapSingle(basisSubstanceService
						.getById(basisSubstanceId).getBasisSubstanceType().getId(), filterText);
	}
}
