package com.cms.szy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @Description TODO(系统页面视图) 
 * @ClassName PageController 
 * @author ShenZiYang 
 * @date 2018年1月6日 下午5:43:13
 */
@Controller
public class PageController {

	@RequestMapping("modules/{module}/{url}.html")
	public String module(@PathVariable("module") String module, @PathVariable("url") String url){
		return "modules/" + module + "/" + url;
	}

	@RequestMapping("{url}.html")
	public String url(@PathVariable("url") String url){
		return url;
	}

	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	
	
}
