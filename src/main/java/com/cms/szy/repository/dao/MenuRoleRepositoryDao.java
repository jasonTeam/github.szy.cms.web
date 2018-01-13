package com.cms.szy.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cms.szy.configuration.query.core.BaseRepository;
import com.cms.szy.entity.po.MenuRole;

/**
 * 
 * (角色菜单关系dao层接口) 
 * @ClassName MenuRoleRepositoryDao 
 * @author ShenZiYang 
 * @date 2018年1月13日 下午4:40:09
 */
public interface MenuRoleRepositoryDao extends BaseRepository<MenuRole, Long>{
	
	/**
	 * 
	 *【根据角色Id获取相应的菜单按钮】 
	 * @param roleId
	 * @return List<Long>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月13日下午4:48:14
	 * @throws 异常
	 */
	@Query("SELECT mr.menuId FROM MenuRole mr WHERE mr.roleId = ?1")
	List<Long> queryMenuByRoleId(Long roleId);
	
}
