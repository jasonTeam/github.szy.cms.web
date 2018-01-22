package com.cms.szy.repository.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
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
	
	/**
	 * 
	 *【根据角色id和菜单id获取菜单角色关系实体类】
	 * @Title menuRoleList 
	 * @param roleId
	 * @param menuId
	 * @return List<MenuRole>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月21日上午11:39:22
	 * @throws  异常
	 */
	@Query("SELECT mr FROM MenuRole mr WHERE mr.roleId = ?1 AND mr.menuId =?2")
	List<MenuRole> menuRoleList(Long roleId, Long menuId);
	
	
	/**
	 * 
	 *【根据roleId删除 菜单与角色的对应关系 】 
	 * @param roleId void返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月22日下午4:24:56
	 * @throws 异常
	 */
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "delete from sys_menu_role WHERE role_id = ?1",nativeQuery = true)
	void deleteMenuRole(Long roleId);
	
	
	
}
