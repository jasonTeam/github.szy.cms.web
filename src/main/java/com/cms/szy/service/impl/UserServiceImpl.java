package com.cms.szy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.cms.szy.configuration.annotation.DataFilter;
import com.cms.szy.configuration.redis.cache.IdGlobalGenerator;
import com.cms.szy.entity.po.Dept;
import com.cms.szy.entity.po.User;
import com.cms.szy.entity.po.UserRole;
import com.cms.szy.entity.vo.UserVO;
import com.cms.szy.enums.IsDeleteEnum;
import com.cms.szy.repository.dao.DeptRepositoryDao;
import com.cms.szy.repository.dao.RoleRepositoryDao;
import com.cms.szy.repository.dao.UserRepositoryDao;
import com.cms.szy.repository.dao.UserRoleRepositoryDao;
import com.cms.szy.repository.queryFilter.UserQuery;
import com.cms.szy.service.UserService;
import com.cms.szy.tools.exception.ImplException;
import com.cms.szy.tools.shiro.ShiroUtils;

/**
 * 
 *(用户业务层接口实现类) 
 * @ClassName UserServiceImpl 
 * @author ShenZiYang 
 * @date 2018年1月6日 上午9:38:03
 */

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepositoryDao userRepositoryDao;
	@Autowired
	private DeptRepositoryDao deptRepositoryDao;
	@Autowired
	private UserRoleRepositoryDao userRoleRepositoryDao;
	@Autowired
	private RoleRepositoryDao roleRepositoryDao;
	@Autowired
	private IdGlobalGenerator idGlobalGenerator;
	
	@Override
	public List<String> getPermsByUser(Long userId) {
		List<String> listPerms = userRepositoryDao.getPermsByUser(userId);
		return listPerms;
	}

	
	@Override
	public List<Long> queryAllMenuId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int updatePwd(Long userId, String oriPassword, String newPassword) {
        User user = userRepositoryDao.findOne(userId);
        if(!user.getPassword().equals(oriPassword)){
        	throw new ImplException("1111", "原密码错误!");
        }
        int res = userRepositoryDao.updatePwd(newPassword, userId);
        
        return res;
	}


//	@Override
//	@DataFilter(tableAlias = "u", user = false)
//	public Page<User> findPageUser(UserVO vo, Integer pageNo, Integer pageSize, String sortField) {
//		//查询条件
//		UserQuery query = new UserQuery();
//		if(StringUtils.isNotEmpty(vo.getUserName())){
//			query.setUserName(vo.getUserName());
//		}
//
//		//过滤删除字段
//		query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
//		//排序
//		Sort sort = new Sort(Direction.ASC,"userId");
//		//分页条件
//		Pageable page = new PageRequest(pageNo, pageSize, sort);
//		//获取分页数据
//		Page<User> pageData =  userRepositoryDao.findAll(query, page);	
//		List<User> userList = pageData.getContent();	
//		
//		//user实体和dept实体dept_id映射
//		Map<Long,Dept> userDeptMap  = new HashMap<>();
//		for(User u : userList){
//			if(null != u.getDeptId() || u.getDeptId() >= 0){
//				userDeptMap.put(u.getDeptId(), deptRepositoryDao.findOne(u.getDeptId()));
//			}
//			
//		}
//		
//		//user实体和userRole实体的userIdyin
//		Map<Long,UserRole> userRoleMap = new HashMap<>();
//		for(User u : userList){
//			userRoleMap.put(u.getUserId(), userRoleRepositoryDao.queryUserRoleByUserId(u.getUserId()));
//			
//		}
//		
//		
//		// 数据拼装
//		for (User u : userList) {
//			u.setDeptName(userDeptMap.get(u.getDeptId()).getName()); // 获取部门名称
//			if(u.getUserId() == 1){
//				u.setRoleName("管理员");
//			}else{
//				Long roleId = userRoleMap.get(u.getUserId()).getRoleId(); // 角色ID
//				u.setRoleName(roleRepositoryDao.findOne(roleId).getRoleName());
//			}
//		}
//			
//		return pageData;
//	}
	
	
	@Override
	@DataFilter(tableAlias = "u", user = false)
	public Page<User> findPageUser(Map<String, Object> params) {
		//查询条件
		UserQuery query = new UserQuery();
//		if(StringUtils.isNotEmpty(vo.getUserName())){
//			query.setUserName(vo.getUserName());
//		}

		//过滤删除字段
		query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		//排序
		Sort sort = new Sort(Direction.ASC,"userId");
		//分页条件
//		Pageable page = new PageRequest(pageNo, pageSize, sort);
		//获取分页数据
//		Page<User> pageData =  userRepositoryDao.findAll(query, page);	
//		List<User> userList = pageData.getContent();	
		
		//user实体和dept实体dept_id映射
//		Map<Long,Dept> userDeptMap  = new HashMap<>();
//		for(User u : userList){
//			if(null != u.getDeptId() || u.getDeptId() >= 0){
//				userDeptMap.put(u.getDeptId(), deptRepositoryDao.findOne(u.getDeptId()));
//			}
//			
//		}
//		
//		//user实体和userRole实体的userIdyin
//		Map<Long,UserRole> userRoleMap = new HashMap<>();
//		for(User u : userList){
//			userRoleMap.put(u.getUserId(), userRoleRepositoryDao.queryUserRoleByUserId(u.getUserId()));
//			
//		}
//		
//		
//		// 数据拼装
//		for (User u : userList) {
//			u.setDeptName(userDeptMap.get(u.getDeptId()).getName()); // 获取部门名称
//			if(u.getUserId() == 1){
//				u.setRoleName("管理员");
//			}else{
//				Long roleId = userRoleMap.get(u.getUserId()).getRoleId(); // 角色ID
//				u.setRoleName(roleRepositoryDao.findOne(roleId).getRoleName());
//			}
//		}
			
//		return pageData;
		return null;
	}

	
	@Override
	@Transactional  //添加事务
	public void saveUser(User user) {
		User newUser = new User();
		newUser.setUserId(idGlobalGenerator.getSeqId(User.class)); // 通过redis生成用户ID
		newUser.setUserName(user.getUserName());  // 登录账号
		newUser.setDeptId(user.getDeptId());  // 所属部门
		newUser.setEmail(user.getEmail());    // 邮箱
		newUser.setMobile(user.getMobile());  // 手机号
		newUser.setStatus(user.getStatus());  // 状态 0：禁用 1：正常
		newUser.setSalt( RandomStringUtils.randomAlphanumeric(20)); // 密码加盐   
		newUser.setPassword(ShiroUtils.sha256(user.getPassword(), newUser.getSalt()));  // 登录密码（sha256加密）
		newUser.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());  // 是否删除
		newUser.setCreateTime(new Date());  // 创建时间
		User userBean = userRepositoryDao.save(newUser);

		/*
		 * 保存用户与角色关系【用户 <——> 角色】
		 * 再新增用户时同时将关系数据保存到用户角色关联表中
		 */
		UserRole newUserRole = new UserRole();
		newUserRole.setId(idGlobalGenerator.getSeqId(UserRole.class));
		newUserRole.setUserId(userBean.getUserId());
		List<Long> roleIdList = user.getRoleIdList();
		for(Long roleId : roleIdList){
			//获取角色Id
			newUserRole.setRoleId(roleId);	
		}
		newUserRole.setCreateTime(new Date()); 
		userRoleRepositoryDao.save(newUserRole);
	}

	@Override
	public User queryUserByUserId(Long userId) {
		return userRepositoryDao.findOne(userId);
	}


	@Override
	@Transactional  //添加事务
	public void updateUser(User user) {
		// 先查询用户是否存在
		User userBean = userRepositoryDao.findOne(user.getUserId());
		if (null != userBean) {
			userBean.setUserName(user.getUserName()); // 用户名
			userBean.setDeptId(user.getDeptId()); // 用户所属部门
			userBean.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt())); // 登录密码
			userBean.setEmail(user.getEmail()); // 邮箱
			userBean.setMobile(user.getMobile()); // 手机号
			userBean.setStatus(user.getStatus()); // 用户状态
			userBean.setIsDelete(IsDeleteEnum.UN_DELETE.getVal()); // 是否删除
			userRepositoryDao.save(userBean); // 保存

			/*
			 * 更新用户与角色之间的关系【用户 <——> 角色】
			 */
			UserRole userRole = userRoleRepositoryDao.queryUserRoleByUserId(user.getUserId()); // 先查询是否记录已存在
			if (null != userRole) {
				List<Long> roleIdList = user.getRoleIdList();
				for (Long roleId : roleIdList) {
					// 更新角色Id
					userRole.setRoleId(roleId);
				}
				userRoleRepositoryDao.save(userRole); //保存数据
			}
		}
	}


	@Override
	@Transactional  //添加事务
	public void deleteBatchUser(Long[] userIds) {
		if(null != userIds && userIds.length > 0){
			for(int i = 0; i < userIds.length; i++){
				User userBean = userRepositoryDao.findOne(userIds[i]); //查询用户数据是否存在
				userBean.setIsDelete(IsDeleteEnum.DELETE.getVal());
				userRepositoryDao.save(userBean);
			}
		}
	}
	
	
	
	
}
