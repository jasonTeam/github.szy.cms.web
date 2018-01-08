package com.cms.szy.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cms.szy.entity.po.User;
import com.cms.szy.entity.vo.UserVO;
import com.cms.szy.repository.dao.UserRepositoryDao;
import com.cms.szy.repository.queryFilter.UserQuery;
import com.cms.szy.service.UserService;
import com.cms.szy.tools.exception.ImplException;

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
	public Page<User> findPageUser(UserVO vo, Integer pageNo, Integer pageSize, String sortField) {
		
		//查询条件
		UserQuery query = new UserQuery();
		if(StringUtils.isEmpty(vo.getUserName())){
			query.setUserName(vo.getUserName());
		}
		
		//排序
		Sort sort = new Sort(Direction.DESC,sortField);
		
		//分页条件
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		
		Page<User> pageData =  userRepositoryDao.findAll(query, page);	
		
		return pageData;
	}
	
	
	
	
}
