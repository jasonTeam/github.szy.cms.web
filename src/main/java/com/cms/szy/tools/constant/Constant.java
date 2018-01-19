package com.cms.szy.tools.constant;

/**
 * 
 *【常量类】  
 * @author ShenZiYang 
 * @date 2018年1月19日 上午11:48:37
 */
public class Constant {
	
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;

	/**
	 * 
	 *【菜单类型】  
	 * @author ShenZiYang 
	 * @date 2018年1月19日 上午11:48:57
	 */
    public enum MenuType {
    	
        /*** 目录 */
    	CATALOG(0),
    	
        /*** 菜单*/
        MENU(1),
        
        /*** 按钮*/
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
}
