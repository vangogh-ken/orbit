package org.cc.automate.config.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cc.automate.config.domain.Environment;
import org.cc.automate.config.service.EnvironmentService;
import org.cc.automate.config.service.Service;
import org.cc.automate.core.BasisParamHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/environments")
public class EnvironmentController extends BaseController{
	@Autowired
	private BasisParamHelper basisParamHelper;
	@Autowired
	private EnvironmentService environmentService;
	/**
	 * 获取列表
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Map<String, Object>> list(HttpServletRequest request){
		return environmentService.query(Service.substanceTypeCache.get(Environment.class.getName()), basisParamHelper.convertParam(request.getParameterMap()));
	}
	
	/**
	 * 添加
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public Map<String, Object> toAdd(HttpServletRequest request){
		return environmentService.toAdd(Service.substanceTypeCache.get(Environment.class.getName()));
	}
	
	/**
	 * 完成添加
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String doneAdd(HttpServletRequest request){
		if(environmentService.doneAdd(Service.substanceTypeCache.get(Environment.class.getName()), "草稿", basisParamHelper.convertParam(request.getParameterMap()))){
			return "success";
		}
		return "error";
	}
	
	
	@RequestMapping(value = "/{basisSubstanceId}", method = RequestMethod.GET)
	public Map<String, Object> toUpdate(@PathVariable String basisSubstanceId){
		return environmentService.toUpdate(basisSubstanceId);
	}
	
	@RequestMapping(value = "/{basisSubstanceId}", method = RequestMethod.PUT)
	public String doneUpdate(@PathVariable String basisSubstanceId, HttpServletRequest request){
		if(environmentService.doneUpdate(basisSubstanceId, "已保存", basisParamHelper.convertParam(request.getParameterMap()))){
			return "success";
		}else{
			return "error";
		}
	}
	
	@RequestMapping(value = "/{basisSubstanceId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String basisSubstanceId){
		if(environmentService.deleteById(basisSubstanceId)){
			return "success";
		}else{
			return "error";
		}
	}
	
	/**
	 * 校验逻辑并生成配置文件
	 * @param basisSubstanceId
	 * @return
	 */
	@RequestMapping(value = "/{basisSubstanceId}/initiate", method = RequestMethod.GET)
	public Map<String, Object> initiate(@PathVariable String basisSubstanceId){
		int version = 1;
		Map<String, Object> result = environmentService.initiate(version, basisSubstanceId);
		if((boolean)result.get("result")){
			result = environmentService.config(version, basisSubstanceId);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/{basisSubstanceId}/execute", method = RequestMethod.GET)
	public Map<String, Object> execute(@PathVariable String basisSubstanceId){
		int version = 1;
		return environmentService.execute(version, basisSubstanceId);
	}
	
}
