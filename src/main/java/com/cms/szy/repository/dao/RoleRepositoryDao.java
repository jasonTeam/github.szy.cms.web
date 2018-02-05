package com.cms.szy.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cms.szy.configuration.query.core.BaseRepository;
import com.cms.szy.entity.po.Role;

public interface RoleRepositoryDao extends BaseRepository<Role, Long>{
	
	/**
	 * 
	 *【根据创建者ID查询角色ID集合】 
	 * @param createUserId 创建者ID
	 * @return List<Long>返回类型   
	 * @author ShenZiYang
	 * @date 2018年2月5日下午2:23:17
	 * @throws 异常
	 */
	@Query("SELECT r.roleId FROM Role r WHERE r.createUserId = ?1")
	List<Long> queryRoleIdList(Long createUserId);
	
	
	
}
