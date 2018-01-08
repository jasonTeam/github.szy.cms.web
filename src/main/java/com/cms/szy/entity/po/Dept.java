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
 * (部门实体类) 
 * @ClassName Dept 
 * @author ShenZiYang 
 * @date 2018年1月8日 下午1:31:46
 */
@Entity
@Table(name = "sys_dept") 
public class Dept implements Serializable{
	

	private static final long serialVersionUID = -6974614561905608885L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dept_id")
	private Long deptId;
	
	/**
	 * 上级部门ID，一级部门为0
	 */
	@Column(name = "parent_id")
	private Long parentId;
	
	@Column(name = "dept_name")
	private String deptName;
	
	@Column(name = "order_num")
	private Integer orderNum;
	
	@Column(name = "is_delete")
	private  Integer isDelete;

	
	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
