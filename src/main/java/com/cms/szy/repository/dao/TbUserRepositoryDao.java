package com.cms.szy.repository.dao;

import org.springframework.data.jpa.repository.Query;

import com.cms.szy.configuration.query.core.BaseRepository;
import com.cms.szy.entity.po.TbUser;

/**
 * 
 * (user表dao层接口) 
 * @ClassName tbUserRepositoryDao 
 * @author ShenZiYang 
 * @date 2017年12月18日 下午5:37:45
 */
public interface TbUserRepositoryDao extends BaseRepository<TbUser, Long>{
	
	
	/**
	 * 
	 * (登录) 
	 * @Title login 登录
	 * @param userName 用户名
	 * @param userPwd 密码
	 * @return TbUser返回类型   
	 * @author ShenZiYang
	 * @date 2017年12月19日上午9:32:49
	 * @throws 异常
	 */
	@Query("SELECT u FROM TbUser u WHERE u.isDelete = 1")
	TbUser login(String userName,String userPwd);
	
	
	
}
