package com.cms.szy.enums;

/**
 * 
 * (管理员枚举类) 
 * @ClassName AdminTypeEnum 
 * @author ShenZiYang 
 * @date 2017年12月11日 下午1:48:38
 */
public enum UserStatusEnum {
	
	NORMAL(Short.valueOf("1"), "正常"),
	LOCK(Short.valueOf("0"), "锁定");
	
	private Short val;   //值 
	private String desc; //描述
	
	//构造器
	private UserStatusEnum(Short val,String desc){
		this.val = val;
		this.desc = desc;
	}
	
	
	public static UserStatusEnum getEnumByVal(Short val){
		if (val == null)
            return null;
        for (UserStatusEnum tSORNOTEnum : UserStatusEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static UserStatusEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (UserStatusEnum tSORNOTEnum : UserStatusEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public Short getVal() {
		return val;
	}

	public String getDesc() {
		return desc;
	}
	
	
	
}
