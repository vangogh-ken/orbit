package org.cc.automate.basis.controller;


import java.util.HashMap;
import java.util.Map;

import org.cc.automate.basis.service.BasisAttributeService;
import org.cc.automate.basis.service.BasisSubstanceTypeService;
import org.cc.automate.core.Pager;
import org.cc.automate.db.entity.BasisAttribute;
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
public class BasisAttributeController {
	@Autowired
	private BasisAttributeService basisAttributeService;
	@Autowired
	private BasisSubstanceTypeService basisSubstanceTypeService;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "basis-attribute-list")
	public String unread(Model model, BasisAttribute basisAttribute, @ModelAttribute Pager pager) {
		if (pager == null) {
			pager = new Pager(1);
		}
		pager = basisAttributeService.query(pager, basisAttribute);

		model.addAttribute("pager", pager);
		return "/basis/basis-attribute-list";
	}

	@RequestMapping(value = "basis-attribute-input")
	public String input(Model model, String id) {
		BasisAttribute item = null;
		if (id != null) {
			item = basisAttributeService.getById(id);
		}
		model.addAttribute("item", item);
		return "/basis/basis-attribute-input";
	}

	@RequestMapping(value = "basis-attribute-save", method = RequestMethod.POST)
	public String add(BasisAttribute basisAttribute, RedirectAttributes redirectAttributes) {
		if(basisAttribute.getId() == null){
			basisAttributeService.add(basisAttribute);
		}else{
			basisAttributeService.modify(basisAttribute);
		}
		return "redirect:basis-attribute-list.do";
	}
	
	@RequestMapping(value = "basis-attribute-remove")
	public String remove(Model model, String[] selectedItem, RedirectAttributes redirectAttributes) {
		for (String id : selectedItem) {
			basisAttributeService.delete(id);
		}
		return "redirect:basis-attribute-list";
	}
	@ResponseBody
	@RequestMapping(value = "basis-attribute-done-add-batch")
	public String doneAddBatch(@RequestBody BasisAttribute basisAttribute, 
			@RequestParam(value="basisSubstanceTypeId",required=true)String basisSubstanceTypeId,
			@RequestParam(value="batchCount",required=true)int batchCount,
			@RequestParam(value="batchStart",required=true)int batchStart,
			@RequestParam(value="batchEnd",required=true)int batchEnd){
		basisAttribute.setBasisSubstanceType(basisSubstanceTypeService.getById(basisSubstanceTypeId));
		basisAttributeService.doneAddBatch(basisAttribute, batchCount, batchStart, batchEnd);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "basis-attribute-to-revise-attribute")
	public Map<String, Object> toReviseActionFiled(@RequestParam(value="basisAttributeId",required=true)String basisAttributeId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basisAttribute", basisAttributeService.getById(basisAttributeId));
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "basis-attribute-done-remove-attribute")
	public String doneRemoveFiled(@RequestParam(value="basisAttributeId",required=true)String[] basisAttributeId){
		for(String id : basisAttributeId){
			basisAttributeService.delete(id);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "basis-attribute-check-attributename")
	public boolean checkName(@RequestParam(required=true, value="attributeName") String attributeName, 
			@RequestParam(required=true, value="basisSubstanceTypeId") String basisSubstanceTypeId, String id) {
		if(id != null){
			String sql = "SELECT COUNT(*) AS count FROM BASIS_ATTR WHERE ATTR_NAME = ? AND BASIS_SUBSTANCE_TYPE_ID = ? AND ID <> ?";
			int count = jdbcTemplate.queryForObject(sql, Integer.class, attributeName, basisSubstanceTypeId, id);
			return count == 0;
		}else{
			String sql = "SELECT COUNT(*) AS count FROM BASIS_ATTR WHERE ATTR_NAME = ? AND BASIS_SUBSTANCE_TYPE_ID = ?";
			int count = jdbcTemplate.queryForObject(sql, Integer.class, attributeName, basisSubstanceTypeId);
			return count == 0;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "basis-attribute-check-attributecolumn")
	public boolean checkColumn(@RequestParam(required=true, value="attributeColumn") String attributeColumn, 
			@RequestParam(required=true, value="basisSubstanceTypeId") String basisSubstanceTypeId, String id) {
		if(!StringUtil.isNullOrEmpty(id)){
			String sql = "SELECT COUNT(*) AS count FROM BASIS_ATTR WHERE ATTR_COLUMN = ? AND BASIS_SUBSTANCE_TYPE_ID = ? AND ID <> ?";
			int count = jdbcTemplate.queryForObject(sql, Integer.class, attributeColumn, basisSubstanceTypeId, id);
			return count == 0;
		}else{
			String sql = "SELECT COUNT(*) AS count FROM BASIS_ATTR WHERE ATTR_COLUMN = ? AND BASIS_SUBSTANCE_TYPE_ID = ?";
			int count = jdbcTemplate.queryForObject(sql, Integer.class, attributeColumn, basisSubstanceTypeId);
			return count == 0;
		}
	}
}
