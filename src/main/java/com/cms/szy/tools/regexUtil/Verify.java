package com.cms.szy.tools.regexUtil;

import java.util.regex.Pattern;

/**
 * 
 *【各类格式校验】  
 * @author ShenZiYang 
 * @date 2018年2月3日 下午3:42:46
 */
public class Verify {
	
	/**
	 * 正则表达式：验证手机号
	 */
	public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	
	
	/**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    
	
    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
    
    
    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
    
    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    
    
	/**
	 * 
	 *【邮箱格式验证】 
	 * @param email 表单提交的邮箱参数
	 * @return boolean返回类型   
	 * @author ShenZiYang
	 * @date 2018年2月3日下午3:55:00
	 * @throws 异常
	 */
    public static boolean isEmail(String email){
    	return Pattern.matches(REGEX_EMAIL, email);
    }
    
    /**
     * 
     *【手机格式校验】 
     * @param mobile 表单提交的手机参数
     * @return boolean返回类型   
     * @author ShenZiYang
     * @date 2018年2月3日下午3:56:41
     * @throws 异常
     */
    public static boolean isPhone(String mobile){
    	return Pattern.matches(REGEX_MOBILE, mobile);
    }
    
    
    
    
}
