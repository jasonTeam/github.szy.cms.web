package com.cms.szy.enums;

/**
 * 
 *【菜单类型枚举类】  
 * @author ShenZiYang 
 * @date 2018年1月19日 上午9:53:08
 */
public enum MenuTypeEnum {
	
	CATALOG(0, "目录"), 
	MENU(1, "菜单"), 
	BUTTON(2, "按钮"),;
	
	//构造器
	private MenuTypeEnum (Integer val,String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	private Integer val;
	private String desc;
		
	public Integer getVal() {
		return val;
	}

	public void setVal(Integer val) {
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


