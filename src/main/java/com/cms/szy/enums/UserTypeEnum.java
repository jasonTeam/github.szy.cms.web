package com.cms.szy.enums;

/**
 * 
 *【用户类型枚举类】  
 * @author ShenZiYang 
 * @date 2018年1月19日 上午9:53:08
 */
public enum UserTypeEnum {
	
	ADMIN(1L,"系统管理员")
	;
	
	//构造器
	private UserTypeEnum (Long val,String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	private Long val;
	private String desc;
		
	public Long getVal() {
		return val;
	}

	public void setVal(Long val) {
		this.val = val;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static UserTypeEnum getEnumByNumber(Long val){
		if (val == null)
            return null;
        for (UserTypeEnum tSORNOTEnum : UserTypeEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static UserTypeEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (UserTypeEnum tSORNOTEnum : UserTypeEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


