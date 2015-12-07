package org.cc.automate.config.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cc.automate.config.domain.NodeHost;
import org.cc.automate.config.service.NodeHostService;
import org.cc.automate.config.service.Service;
import org.cc.automate.core.BasisParamHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/nodehosts")
public class NodeHostController extends BaseController{
	@Autowired
	private BasisParamHelper basisParamHelper;
	@Autowired
	private NodeHostService nodeHostService;
	/**
	 * 获取列表
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Map<String, Object>> list(HttpServletRequest request){
		return nodeHostService.query(Service.substanceTypeCache.get(NodeHost.class.getName()), basisParamHelper.convertParam(request.getParameterMap()));
	}
	
	/**
	 * 添加
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public Map<String, Object> toAdd(HttpServletRequest request){
		return nodeHostService.toAdd(Service.substanceTypeCache.get(NodeHost.class.getName()));
	}
	
	/**
	 * 完成添加
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String doneAdd(HttpServletRequest request){
		if(nodeHostService.doneAdd(Service.substanceTypeCache.get(NodeHost.class.getName()), "草稿", basisParamHelper.convertParam(request.getParameterMap()))){
			return "success";
		}
		return "error";
	}
	
	
	@RequestMapping(value = "/{basisSubstanceId}", method = RequestMethod.GET)
	public Map<String, Object> toUpdate(@PathVariable String basisSubstanceId){
		return nodeHostService.toUpdate(basisSubstanceId);
	}
	
	@RequestMapping(value = "/{basisSubstanceId}", method = RequestMethod.PUT)
	public String doneUpdate(@PathVariable String basisSubstanceId, HttpServletRequest request){
		if(nodeHostService.doneUpdate(basisSubstanceId, "已保存", basisParamHelper.convertParam(request.getParameterMap()))){
			return "success";
		}else{
			return "error";
		}
	}
	
	@RequestMapping(value = "/{basisSubstanceId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String basisSubstanceId){
		if(nodeHostService.deleteById(basisSubstanceId)){
			return "success";
		}else{
			return "error";
		}
	}
}
