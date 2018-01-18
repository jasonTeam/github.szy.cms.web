package com.cms.szy.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cms.szy.entity.po.User;
import com.cms.szy.entity.vo.UserVO;

/**
 * 
 * (用户业务层接口类) 
 * @ClassName UserService 
 * @author ShenZiYang 
 * @date 2018年1月6日 上午9:37:08
 */
public interface UserService {
	
	/**
	 * 
	 * (分页查询用户数据列表) 
	 * @Title findPageUser 
	 * @param vo
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return Page<User>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月8日下午3:56:05
	 * @throws 异常
	 */
	Page<User> findPageUser(UserVO vo ,Integer pageNo,Integer pageSize,String sortField);
	
	
	/**
	 * 
	 * (根据用户ID查询用户的所有权限) 
	 * @Title getPermsByUser 
	 * @param userId
	 * @return List<String>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日下午12:28:02
	 * @throws 查询失败
	 */
	List<String> getPermsByUser(Long userId);
	
	
	/**
	 * 
	 * (查询用户的所有菜单ID) 
	 * @Title queryAllMenuId 
	 * @param userId
	 * @return List<Long>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月6日下午2:18:14
	 * @throws 查询失败
	 */
	List<Long> queryAllMenuId(Long userId);
	
	
	/**
	 * 
	 * (修改用戶密码) 
	 * @Title updatePwd 
	 * @param userId
	 * @param oriPassword
	 * @param newPassword
	 * @return int返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月8日下午2:27:17
	 * @throws 异常
	 */
	int updatePwd(Long userId, String oriPassword, String newPassword);
	
	/**
	 * 
	 * (新增用户信息) 
	 * @Title addUser 
	 * @param user void返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月10日下午8:43:41
	 * @throws 增加失败
	 */
	void saveUser(User user);
	
	/**
	 * 
	 *【通过用户ID查询用户】 
	 * @param userId
	 * @return User返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月18日下午6:27:21
	 * @throws 异常
	 */
	User queryUserByUserId(Long userId);
	
	
	/**
	 * 
	 *【更新用户信息】
	 * @Title updateUser 
	 * @param user
	 * @return User返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月18日下午10:17:46
	 * @throws  异常
	 */
	void updateUser(User user);
	
	
	
}
