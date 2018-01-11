package com.cms.szy.service;

import org.springframework.data.domain.Page;

import com.cms.szy.entity.po.Role;
import com.cms.szy.entity.vo.RoleVO;

/**
 * 
 * 角色业务层接口
 * @ClassName RoleService 
 * @author ShenZiYang 
 * @date 2018年1月11日 下午4:47:44
 */
public interface RoleService {
	
	/**
	 * 
	 * (分页查询角色列表) 
	 * @Title findPageRole 
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return Page<Role>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月11日下午4:49:13
	 * @throws 查询异常
	 */
	Page<Role> findPageRole(RoleVO vo,Integer pageNo,Integer pageSize,String sortField);
	
	
}
