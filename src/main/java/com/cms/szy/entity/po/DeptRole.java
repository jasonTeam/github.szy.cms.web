package com.cms.szy.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * (部门角色关联表实体类)
 * 
 * @ClassName DeptRole
 * @author ShenZiYang
 * @date 2018年1月8日 下午1:36:12
 */
@Entity
@Table(name = "sys_dept_role") 
public class DeptRole implements Serializable{

	private static final long serialVersionUID = -2378294478809222090L;
	
	/**
	 * 关联表主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	/**
	 * 角色Id
	 */
	@Column(name = "role_id")
	private Long roleId;

	/**
	 * 部门Id
	 */
	@Column(name = "dept_id")
	private Long deptId;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

}
