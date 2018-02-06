package com.cms.szy.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cms.szy.configuration.query.core.BaseRepository;
import com.cms.szy.entity.po.UserRole;

/**
 * 
 *【用户角色关系表Dao层接口】
 * @ClassName UserRoleRepositoryDao 
 * @author ShenZiYang 
 * @date 2018年1月18日 下午9:10:12
 */
public interface UserRoleRepositoryDao extends BaseRepository<UserRole, Long>{
	
	/**
	 * 
	 *【根据用户ID查询对应的角色ID】
	 * @Title queryRoleIdByUserId 
	 * @param userId
	 * @return List<Long>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月18日下午9:11:58
	 * @throws  异常
	 */
	@Query("SELECT ur.roleId FROM UserRole ur WHERE ur.userId = ?1")
	List<Long> queryRoleIdByUserId(Long userId);
	
	
	/**
	 * 
	 *【根据用户Id获取用户角色实体】 
	 * @return UserRole返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月20日下午2:25:20
	 * @throws 异常
	 */
	@Query("SELECT ur FROM UserRole ur WHERE ur.userId = ?1 ")
	UserRole queryUserRoleByUserId(Long userId);
	
}
