package com.cms.szy.entity.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cms.szy.entity.dto.UserDTO;


/**
 * 
 * (用户实体类)
 * 
 * @ClassName User
 * @author ShenZiYang
 * @date 2018年1月5日 下午2:55:00
 */
@Entity
@Table(name = "sys_user")
public class User extends UserDTO implements Serializable {

	private static final long serialVersionUID = -6910527803052728069L;
	
	/**
	 * 用户ID主键
	 */
	@Id
	@Column(name = "user_id")
	private Long userId;
	
	/*** 用户名 */
	@Column(name = "user_name")
	private String userName;
	
	/*** 密码 */
	@Column(name = "password")
	private String password;
	
	/**
	 * 盐
	 */
	@Column(name = "salt")
	private String salt;

	/*** 邮箱 */
	@Column(name = "email")
	private String email;

	/**
	 * 手机
	 */
	@Column(name = "mobile")
	private String mobile;
	
	/**
	 * 用户状态(0：禁用 , 1：正常)
	 */
	@Column(name = "status")
	private Short status;
	
	/**
	 * 创建者ID
	 */
	@Column(name = "create_user_id")
	private Long createUserId;
	
	/**
	 * 部门ID
	 */
	@Column(name = "dept_id")
	private Long deptId;
	
	/**
	 * 1：正常，-1：删除
	 */
	@Column(name = "is_delete")
	private Short isDelete;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	
	/*-------------------------------------------
    |  A C C E S S O R S / M O D I F I E R S    |
    ============================================*/
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	
	
	

}
