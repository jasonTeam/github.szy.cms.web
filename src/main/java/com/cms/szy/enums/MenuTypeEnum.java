package com.cms.szy.enums;

/**
 * 
 *【菜单类型枚举类】  
 * @author ShenZiYang 
 * @date 2018年1月19日 上午9:53:08
 */
public enum MenuTypeEnum {
	
	CATALOG(1L,"目录"),
	MENU(1L,"菜单"),
	BUTTON(2L,"按钮"),
	;
	
	//构造器
	private MenuTypeEnum (Long val,String desc) {
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

	public static MenuTypeEnum getEnumByNumber(Long val){
		if (val == null)
            return null;
        for (MenuTypeEnum tSORNOTEnum : MenuTypeEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static MenuTypeEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (MenuTypeEnum tSORNOTEnum : MenuTypeEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


