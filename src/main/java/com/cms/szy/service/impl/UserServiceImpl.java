package com.cms.szy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cms.szy.configuration.annotation.DataFilter;
import com.cms.szy.configuration.redis.cache.IdGlobalGenerator;
import com.cms.szy.entity.po.Dept;
import com.cms.szy.entity.po.User;
import com.cms.szy.entity.vo.UserVO;
import com.cms.szy.repository.dao.DeptRepositoryDao;
import com.cms.szy.repository.dao.UserRepositoryDao;
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


	@Override
	@DataFilter(tableAlias = "r", user = false)
	public Page<User> findPageUser(UserVO vo, Integer pageNo, Integer pageSize, String sortField) {
		//查询条件
		UserQuery query = new UserQuery();
		if(StringUtils.isEmpty(vo.getUserName())){
			query.setUserName(vo.getUserName());
		}
		//排序
		Sort sort = new Sort(Direction.ASC,sortField);
		//分页条件
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		//获取分页数据
		Page<User> pageData =  userRepositoryDao.findAll(query, page);	
		List<User> userList = pageData.getContent();	
		//user实体和dept实体dept_id映射
		Map<Long,Dept> userDeptMap  = new HashMap<>();
		for(User u : userList){
			userDeptMap.put(u.getDeptId(), deptRepositoryDao.findOne(u.getDeptId()));
		}
		//数据拼装
		for(User u : userList){
			u.setDeptName(userDeptMap.get(u.getDeptId()).getDeptName());//获取部门名称
		}
		return pageData;
	}

	@Override
	public void saveUser(User user) {
		User newUser = new User();
		Long userId = idGlobalGenerator.getSeqId(User.class);
		newUser.setUserId(userId); //用户ID
		newUser.setUserName(user.getUserName()); //登录账号
		newUser.setDeptId(1L); //所属部门
		newUser.setEmail(user.getEmail()); //邮箱
		newUser.setMobile(user.getMobile()); //手机号
		newUser.setStatus(user.getStatus()); //状态  0：禁用   1：正常
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		newUser.setSalt(salt); 
		newUser.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt())); //登录密码
		newUser.setCreateTime(new Date()); //创建时间
		userRepositoryDao.save(newUser);
	}


	@Override
	public User queryUserByUserId(Long userId) {
		return userRepositoryDao.findOne(userId);
	}
	
	
	
	
}
