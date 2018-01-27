package com.cms.szy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.szy.configuration.log.GwsLogger;

/**
 * 
 *【系统页面视图】
 * @ClassName PageController 
 * @author ShenZiYang 
 * @date 2018年1月27日 下午10:38:48
 */
@Controller
public class PageController {

	//原来的代码
//	@RequestMapping("modules/{module}/{url}.html")
//	public String module(@PathVariable("module") String module, @PathVariable("url") String url){
//		return "modules/" + module + "/" + url;
//	}
	
	@RequestMapping("/{module}/{url}.html")
	public String module(@PathVariable("module") String module, @PathVariable("url") String url){
		GwsLogger.info("系统页面===>:/module={}/url={}",module,url);
		return "/" + module + "/" + url;
	}

	@RequestMapping("{url}.html")
	public String url(@PathVariable("url") String url){
		GwsLogger.info("系统页面===>:/url={}",url);
		return url;
	}

	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	
	
}
