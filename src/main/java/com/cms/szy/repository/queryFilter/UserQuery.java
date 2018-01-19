package com.cms.szy.repository.queryFilter;

import com.cms.szy.configuration.query.Where;
import com.cms.szy.configuration.query.annotation.QBindAttrField;
import com.cms.szy.configuration.query.annotation.QBindEntity;
import com.cms.szy.configuration.query.core.BaseQuery;
import com.cms.szy.entity.po.User;

/**
 * 
 * (用户查询构造条件) 
 * @ClassName UserQuery 
 * @author ShenZiYang 
 * @date 2018年1月8日 下午3:57:38
 */
@QBindEntity(entityClass = User.class)
public class UserQuery extends BaseQuery{
	
	@QBindAttrField(fieldName = "userName", where = Where.equal)
	private String userName;
	
	@QBindAttrField(fieldName = "isDelete", where = Where.equal)
	private Short isDelete;
	
	
	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
	
}
