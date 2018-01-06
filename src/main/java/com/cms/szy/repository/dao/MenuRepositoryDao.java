package com.cms.szy.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cms.szy.configuration.query.core.BaseRepository;
import com.cms.szy.entity.po.Menu;

/**
 * 
 * (菜单dao层接口) 
 * @ClassName MenuRepositoryDao 
 * @author ShenZiYang 
 * @date 2018年1月6日 上午10:09:07
 */
public interface MenuRepositoryDao extends BaseRepository<Menu, Long>{
	

	/**
	 * 使用这种jpa查询时不能写实体类名，字段与数据库名字一致
	 * 查询菜单实体集合(查询菜单实体集合) 
	 * @Title menuList 
	 * @return List<Menu>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日上午11:39:31
	 * @throws 查询失败
	 */
	@Query(value = "SELECT m.*,(SELECT p.menu_name FROM sys_menu p WHERE p.menu_id = m.parent_id) AS parentName FROM sys_menu m",nativeQuery = true)
	List<Menu> menuList();
	
	
	/**
	 * 
	 * (根据父菜单查询子菜单) 
	 * @Title queryMenuByParentId 
	 * @param parentId
	 * @return List<Menu>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日下午1:57:39
	 * @throws 查询失败
	 */
	@Query("SELECT m FROM Menu m WHERE m.parentId = ?1 ORDER BY m.sort ")
	List<Menu> queryMenuByParentId(Long parentId);
	
	
	
}
