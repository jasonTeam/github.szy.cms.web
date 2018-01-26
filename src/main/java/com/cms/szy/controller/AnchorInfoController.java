package com.cms.szy.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cms.szy.configuration.log.GwsLogger;
import com.cms.szy.entity.po.AnchorInfo;
import com.cms.szy.entity.vo.AnchorInfoVO;
import com.cms.szy.service.AnchorInfoService;
import com.cms.szy.tools.constant.CommConstant;
import com.cms.szy.tools.result.Ret;
import com.cms.szy.tools.validator.ValidatorUtils;
import com.cms.szy.tools.validator.group.AddGroup;
import com.cms.szy.tools.validator.group.UpdateGroup;

@RestController
@RequestMapping("/anchor/info")
public class AnchorInfoController {
	
	@Autowired
	private AnchorInfoService anchorInfoService;
	
	/**
	 * 
	 *【分页查询所有主播信息列表】 
	 * @param vo
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月19日上午10:32:31
	 * @throws 异常
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@RequiresPermissions("anchor:info:list")
	public Ret anchorInfoList(AnchorInfoVO vo) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		Long startTime = System.currentTimeMillis();
		GwsLogger.info("分页查询所有主播列表信息开始:code={},message={},startTime={}",code,message,startTime);
		
		Page<AnchorInfo> pageData = null;
		try{
			pageData = anchorInfoService.getPageAnchorInfo(vo, vo.getPageNo()-1, vo.getPageSize(), "id");
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("分页查询所有主播列表信息异常:code={},message={},e={}", code, message, e);
		}
		
		Long endTime = System.currentTimeMillis() - startTime;
		GwsLogger.info("分页查询所有主播列表信息结束,code={},message={},endTime={}", code, message,endTime);
		return Ret.ok().put("page", pageData);
	}
	
	
	/**
	 * 
	 *【新增主播信息】 
	 * @param anchorInfo
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月26日下午3:57:37
	 * @throws 异常
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("anchor:info:save")
	public Ret saveUser(@RequestBody AnchorInfo anchorInfo){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		Long startTime = System.currentTimeMillis();
		GwsLogger.info("新增主播信息开始:code={},message={},startTime={}",code,message,startTime);
		
		try{
			ValidatorUtils.validateEntity(anchorInfo, AddGroup.class);
			anchorInfoService.saveAnchorInfo(anchorInfo);
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("分页查询所有主播列表信息异常:code={},message={},e={}", code, message, e);
		}
		
		Long endTime = System.currentTimeMillis() - startTime;
		GwsLogger.info("新增主播信息结束:code={},message={},endTime={}",code,message,endTime);
		return Ret.ok();
	}
	
	
	/**
	 * 
	 *【根据主播ID查询主播信息，用于页面回显】 
	 * @param userId
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月18日下午6:25:54
	 * @throws 异常
	 */
	@RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
	@RequiresPermissions("anchor:info:info")
	public Ret info(@PathVariable("id") Long id){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		Long startTime = System.currentTimeMillis();
		GwsLogger.info("根据主播ID查询主播信息开始:code={},message={},startTime={}",code,message,startTime);
		
		AnchorInfo anchor = null;
		try{	
			anchor = anchorInfoService.queryAnchorById(id);
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据主播ID查询主播信息异常:code={},message={},e={}", code, message, e);
		}
		
		Long endTime = System.currentTimeMillis() - startTime;
		GwsLogger.info("根据主播ID查询主播信息结束:code={},message={},endTime={}",code,message,endTime);
		return Ret.ok().put("AnchorInfo", anchor);
	}
	
	
	/**
	 * 
	 *【修改主播信息】 
	 * @param user
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月26日下午4:18:36
	 * @throws 异常
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@RequiresPermissions("anchor:info:update")
	public Ret update(@RequestBody AnchorInfo anchorInfo){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		Long startTime = System.currentTimeMillis();
		GwsLogger.info("修改主播信息开始:code={},message={},startTime={}",code,message,startTime);
		
		try{
			ValidatorUtils.validateEntity(anchorInfo, UpdateGroup.class);
			anchorInfoService.updateAnchorInfo(anchorInfo);
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("修改主播信息异常:code={},message={},e={}", code, message, e);
		}
		
		Long endTime = System.currentTimeMillis() - startTime;
		GwsLogger.info("修改主播信息结束:code={},message={},endTime={}",code,message,endTime);
		return Ret.ok();
	}
	
	
	/**
	 * 
	 *【批量删除主播信息,物理删除】 
	 * @param userIds
	 * @return R返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月19日上午9:30:37
	 * @throws 异常
	 */
	@RequestMapping(value = "/delete",method  = RequestMethod.POST)
	@RequiresPermissions("anchor:info:delete")
	public Ret delete(@RequestBody Long[] ids) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		Long startTime = System.currentTimeMillis();
		GwsLogger.info("删除主播信息开始:code={},message={},startTime={}",code,message,startTime);
		
		try{
			anchorInfoService.deleteBatchAnchor(ids);
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("删除主播信息异常:code={},message={},e={}", code, message, e);
		}
		
		Long endTime = System.currentTimeMillis() - startTime;
		GwsLogger.info("删除主播信息结束:code={},message={},endTime={}",code,message,endTime);
		return Ret.ok();
	}
	
	
	
}
