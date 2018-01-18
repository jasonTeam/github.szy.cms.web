package com.cms.szy.enums;

/**
 * 
 *【是否删除】
 * @ClassName IsDeleteEnum 
 * @author ShenZiYang 
 * @date 2018年1月18日 下午10:55:01
 */
public enum IsDeleteEnum {
	
	UN_DELETE(Short.parseShort("1"),"未删除"),
	DELETE(Short.parseShort("-1"),"已删除"),
	;
	
	private Short val;    //值
	private String desc;  //描述

	private IsDeleteEnum (Short val,String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	public Short getVal() {
		return val;
	}
	public String getDesc() {
		return desc;
	}
		
	public static IsDeleteEnum getEnumByNumber(Short val){
		if (val == null)
            return null;
        for (IsDeleteEnum tSORNOTEnum : IsDeleteEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static IsDeleteEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (IsDeleteEnum tSORNOTEnum : IsDeleteEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


