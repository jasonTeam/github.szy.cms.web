package com.cms.szy.service;


import com.cms.szy.entity.po.TbUser;
import com.cms.szy.entity.vo.TbUserVO;

public interface TbUserService {
	
	
	/**
	 * 
	 * (用户登录) 
	 * @Title login 登录
	 * @param userName 用户名
	 * @param userPwd 密码
	 * @return TbUser返回类型   
	 * @author ShenZiYang
	 * @date 2017年12月18日下午5:39:37
	 * @throws 登录异常
	 */
	TbUser login(String userName,String userPwd);
	
	/**
	 * 
	 * (根据用户ID获取用户信息) 
	 * @Title getUserById 
	 * @param userId
	 * @return TbUser返回类型   
	 * @author ShenZiYang
	 * @date 2017年12月26日下午3:57:47
	 * @throws 异常
	 */
	TbUser getUserById(Long userId);
	
	/**
	 * 
	 * (插入用户数据) 
	 * @Title saveUser 
	 * @param vo
	 * @return TbUser返回类型   
	 * @author ShenZiYang
	 * @date 2017年12月26日下午4:36:49
	 * @throws
	 */
	TbUser saveUser(TbUserVO vo);
	
	
	
	
}
