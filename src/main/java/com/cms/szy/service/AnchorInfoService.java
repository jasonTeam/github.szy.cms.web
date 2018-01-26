package com.cms.szy.service;

import org.springframework.data.domain.Page;

import com.cms.szy.entity.po.AnchorInfo;
import com.cms.szy.entity.vo.AnchorInfoVO;

public interface AnchorInfoService {
		
	/**
	 * 	
	 *【分页查询主播信息】 
	 * @param vo
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return Page<AnchorInfo>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月26日下午2:09:28
	 * @throws 异常
	 */
	Page<AnchorInfo> getPageAnchorInfo(AnchorInfoVO vo ,Integer pageNo,Integer pageSize,String sortField);
	
	
	/**
	 * 
	 *【插入主播信息】 
	 * @param anchorInfo void返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月26日下午4:05:04
	 * @throws 异常
	 */
	void saveAnchorInfo(AnchorInfo anchorInfo);
	
	
	/**
	 * 
	 *【根据id查询主播信息】 
	 * @param id
	 * @return AnchorInfo返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月26日下午4:14:49
	 * @throws 异常
	 */
	AnchorInfo queryAnchorById(Long id);
	
	
	/**
	 * 
	 *【修改主播信息】 
	 * @param anchorInfo void返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月26日下午4:19:56
	 * @throws 异常
	 */
	void updateAnchorInfo(AnchorInfo anchorInfo);
	
	/**
	 * 
	 *【删除主播信息】 
	 * @param ids void返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月26日下午4:36:25
	 * @throws 异常
	 */
	void deleteBatchAnchor(Long[] ids);
	
	
}
