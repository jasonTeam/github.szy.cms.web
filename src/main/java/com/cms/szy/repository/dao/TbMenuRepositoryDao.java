package com.cms.szy.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cms.szy.configuration.query.core.BaseRepository;
import com.cms.szy.entity.po.TbMenu;

public interface TbMenuRepositoryDao extends BaseRepository<TbMenu, Long>{
		
	/**
	 * 
	 * 查询所有的子菜单
	 * 
	 * @author zengjq 2017年4月18日
	 * @return
	 */
	@Query("select m from TbMenu m where m.parentId is not null and m.isDelete = 1")
	List<TbMenu> listAllSubMenu();
	
	
}
