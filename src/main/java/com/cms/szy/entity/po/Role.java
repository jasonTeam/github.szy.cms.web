package com.cms.szy.entity.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cms.szy.entity.dto.RoleDTO;

/**
 * 
 * (角色表实体类)
 * 
 * @ClassName Role
 * @author ShenZiYang
 * @date 2018年1月8日 下午1:45:18
 */
@Entity
@Table(name = "sys_role")
public class Role extends RoleDTO implements Serializable{

	private static final long serialVersionUID = -2817569474213523738L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "dept_id")
	private Long deptId;

	@Column(name = "remark")
	private String remark;

	@Column(name = "create_time")
	private Date createTime;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	

}
