package com.cms.szy.tools.shiro;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cms.szy.entity.po.Menu;
import com.cms.szy.entity.po.User;
import com.cms.szy.repository.dao.MenuRepositoryDao;
import com.cms.szy.repository.dao.UserRepositoryDao;
import com.cms.szy.tools.constant.Constant;



/**
 * 
 * (认证) 
 * @ClassName UserRealm 
 * @author ShenZiYang 
 * @date 2018年1月6日 上午9:53:04
 */
@Component
public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private UserRepositoryDao userRepositoryDao;
	@Autowired
	private MenuRepositoryDao menuRepositoryDao;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		User user = (User)principals.getPrimaryPrincipal();
		Long userId = user.getUserId(); //获取用户ID
		
		//系统管理员，拥有最高权限
		List<String> permsList = null;
		if (userId == Constant.ADMIN) {
			List<Menu> menuList = menuRepositoryDao.findAll(); //查询所有的菜单
//			permsList = new ArrayList<>(menuList.size());
			permsList = new ArrayList<>();
			for (Menu menu : menuList) {
				//遍历所有的菜单,存入到集合
				permsList.add(menu.getPerms());
			}
			
		}else{
			//如果不是系统管理员,根据用户ID查询所拥有的菜单
			permsList = userRepositoryDao.getPermsByUser(userId);
		}
		
		// 用户权限列表  （HashSet存value不能重复的对象)
		Set<String> permsSet = new HashSet<String>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			
			//将集合中的数据按,号分割，并转成数组存入HashSet中
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	
	/**
	 * 
	 *【登录时调用】 
	 * @Title doGetAuthenticationInfo 
	 * @param authcToken
	 * @return
	 * @throws AuthenticationException 返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月21日下午5:32:03
	 * @throws  异常
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		
		User user = userRepositoryDao.queryByUserName(token.getUsername()); //获取用户信息
		
		//账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
		
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
		return info;
	}
	
	
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
		shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
		shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
		super.setCredentialsMatcher(shaCredentialsMatcher);
	}
	
	
}
