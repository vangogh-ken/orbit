package org.cc.automate.config.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cc.automate.config.domain.Environment;
import org.cc.automate.config.service.EnvironmentService;
import org.cc.automate.core.BasisParamHelper;
import org.cc.automate.core.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Pager list(HttpServletRequest request){
		Pager pager = new Pager(10, 1);
		pager.setResults(environmentService.query(Environment.class, basisParamHelper.convertParam(request.getParameterMap())));
		return pager;
	}
	
	/**
	 * 添加
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public Map<String, Object> toAdd(HttpServletRequest request){
		return environmentService.toAdd(Environment.class);
	}
	
	/**
	 * 完成添加
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String doneAdd(HttpServletRequest request){
		Map<String, Object> params = basisParamHelper.convertParam(request.getParameterMap());
		
		if(environmentService.doneAdd(Environment.class, "草稿", environmentService.initStoragescheme(params))){
			return "success";
		}
		return "error";
	}
	
	@RequestMapping(value = "/{basisSubstanceId}", method = RequestMethod.GET)
	public Map<String, Object> toUpdate(@PathVariable String basisSubstanceId){
		return environmentService.toUpdate(basisSubstanceId);
	}
	
	@RequestMapping(value = "/{basisSubstanceId}/topologies", method = RequestMethod.GET)
	public Map<String, Object> topologies(@PathVariable String basisSubstanceId){
		return environmentService.topologies(basisSubstanceId);
	}
	//检查环境名称是否重复
	@RequestMapping(value = "checkName", method = RequestMethod.GET)
	public Map<String, Object> checkName(@RequestParam(required=false) String basisSubstanceId, 
			@RequestParam String HJMC){
		return environmentService.checkName(basisSubstanceId, HJMC);
	}
	
	@RequestMapping(value = "/{basisSubstanceId}", method = RequestMethod.PUT)
	public String doneUpdate(@PathVariable String basisSubstanceId, HttpServletRequest request){
		Map<String, Object> params = basisParamHelper.convertParam(request.getParameterMap());
		if(environmentService.doneUpdate(basisSubstanceId, "已保存", environmentService.initStoragescheme(params))){
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
	
	@RequestMapping(value = "/{basisSubstanceId}/deploy", method = RequestMethod.GET)
	public Map<String, Object> deploy(@PathVariable String basisSubstanceId){
		int version = 1;
		return environmentService.deploy(version, basisSubstanceId);
	}
	
	//NODEHOST
	@RequestMapping(value = "/{environmentId}/nodehosts", method = RequestMethod.GET)
	public Map<String, Object> nodehosts(@PathVariable String environmentId){
		return environmentService.nodehosts(environmentId);
	}
	
	@RequestMapping(value = "/{environmentId}/nodehosts/scan", method = RequestMethod.GET)
	public Map<String, Object> nodehostsScan(@PathVariable String environmentId){
		return environmentService.nodehostsScan(environmentId);
	}
	
	@RequestMapping(value = "/{environmentId}/nodehosts/{nodehostId}", method = RequestMethod.GET)
	public Map<String, Object> getNodehost(@PathVariable String environmentId, @PathVariable String nodehostId){
		return environmentService.getNodehost(environmentId, nodehostId);
	}
	
	@RequestMapping(value = "/{environmentId}/nodehosts/{nodehostId}", method = RequestMethod.POST)
	public Map<String, Object> addNodehost(@PathVariable String environmentId, @PathVariable String nodehostId){
		return environmentService.addNodehost(environmentId, nodehostId);
	}
	
	@RequestMapping(value = "/{environmentId}/nodehosts/{nodehostId}", method = RequestMethod.DELETE)
	public Map<String, Object> deleteNodehost(@PathVariable String environmentId, @PathVariable String nodehostId){
		return environmentService.deleteNodehost(environmentId, nodehostId);
	}
	
	//STORAGESCHEME
	@RequestMapping(value = "/{environmentId}/storageschemes", method = RequestMethod.GET)
	public Map<String, Object> storageschemes(@PathVariable String environmentId){
		return environmentService.storageschemes(environmentId);
	}
	
}
