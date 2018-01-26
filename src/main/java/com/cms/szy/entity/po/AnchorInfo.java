package com.cms.szy.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "anchor_info") 
public class AnchorInfo implements Serializable{

	private static final long serialVersionUID = -6183138110016636590L;
	
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nick_name")
	private String nickName;
	
	@Column(name = "anchor_sex")
	private Short anchorSex;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "remark")
	private String remark;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Short getAnchorSex() {
		return anchorSex;
	}

	public void setAnchorSex(Short anchorSex) {
		this.anchorSex = anchorSex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
