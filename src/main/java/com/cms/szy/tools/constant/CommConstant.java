package com.cms.szy.tools.constant;

public class CommConstant {
	
	// http请求返回信息
	public static final String GWSSUCC = "success";
	public static final String GWS1001 = "访问资源不存在，或者服务器异常";
	public static final String GWS1002 = "服务器拒绝连接，或者通讯超时";
	public static final String PARAMETER = "必须参数不能为空!";
	
	// http请求返回信息
	public static final String GWSCOD0000 = "000";
	public static final String GWSMSG0000 = "success";
	
	public static final String GWSCOD0001 = "001";
	public static final String GWSMSG0001 = "系统响应异常,操作失败";
	
	public static final String GWSCOD0002 = "002";
	public static final String GWSMSG0002 = "数据获取失败,非法请求,token不正确";
	
	public static final String GWSCOD0003 = "003";
	public static final String GWSMSG0003 = "请求参数错误!";
	
	public static final String GWSCOD0004 = "004";
	public static final String GWSMSG0004 = "需要修改的数据不存在!";
	
	public static final String GWSCOD0005 = "005";
	public static final String GWSMSG0005 = "手机号或密码不能为空!";
	
	public static final String GWSCOD0006 = "006";
	public static final String GWSMSG0006 = "必须参数不能为空!";
	
	//增,删,改,查,异常信息
	public static final String GWSCOD1101 = "1101";
	public static final String GWSMSG1101 = "查询数据失败!";
	
	public static final String GWSCOD1121 = "1121";
	public static final String GWSMSG1121 = "查询时间跨度最大仅支持一个月!";
	
	public static final String GWSCOD1102 = "1102";
	public static final String GWSMSG1102 = "删除数据失败!";
	
	public static final String GWSCOD1103 = "1103";
	public static final String GWSMSG1103 = "修改数据失败!";
	
	public static final String GWSCOD1104 = "1104";
	public static final String GWSMSG1104 = "保存(增加)数据失败!";
	
	public static final String GWSCOD1105 = "1105";
	public static final String GWSMSG1105 = "导出数据失败!";
	
	
	//**************用户相关****************
	public static final String GWSCOD2101 = "2101";
	public static final String GWSMSG2101 = "当前用户不存在!";
	
	public static final String GWSCOD2102 = "2102";
	public static final String GWSMSG2102 = "当前用户账户已冻结,请联系管理员!";
	
	public static final String GWSCOD2103 = "2103";
	public static final String GWSMSG2103 = "手机号不存在!";
	
	public static final String GWSCOD2104 = "2104";
	public static final String GWSMSG2104 = "手机号格式错误!";
	
	public static final String GWSCOD2105 = "2105";
	public static final String GWSMSG2105 = "登录失败!";
	
	public static final String GWSCOD2106 = "2106";
	public static final String GWSMSG2106 = "根据RoleId查询角色权限失败!";
	
	public static final String GWSCOD2107 = "2107";
	public static final String GWSMSG2107 = "登录密码错误!";
	
	public static final String GWSCOD2108 = "2108";
	public static final String GWSMSG2108 = "原手机号码错误!";
	

	//****************************************短信验证码相关******************************************
	//后台管理员登录短信验证码保存前缀
	public static final String CNANNEL_MANAGE_ADMIN_LOGIN_PREFIX = "channel_manage_admin_login_prefix_";
	
	//后台管理员登录短信验证码保存时间
	public static final Long CNANNEL_MANAGE_ADMIN_LOGIN_TIMEOUT = 180L;
	
	public static final String CHANNEL_LOGIN_MANAGE_TIMESTEMP = "channel_login_manage_timestemp";
	public static final Long CHANNEL_MANAGE_LOGIN_TIME_OUT = 30L;  // 重复提交时间
	
	public static final String GWSCOD3101 = "3101";
	public static final String GWSMSG3101 = "发送管理员登录验证码失败!";
	
	public static final String GWSCOD3102 = "3102";
	public static final String GWSMSG3102 = "30秒之内不能重复发送短信验证码!";
	
	public static final String GWSCOD3103 = "3103";
	public static final String GWSMSG3103 = "短信验证码错误或失效!";
	
	public static final String GWSCOD3104 = "3104";
	public static final String GWSMSG3104 = "数据库中已存在上架活动！";
	
	//查询排序字段
	public static final String SORT_FIELD_CREATE = "gmtCreate";
	
	//查询排序字段
	public static final String SORT_CREATE_DATE = "createDate";
	
	//查询排序字段
	public static final String SORT_CTIME_DATE = "ctime";
	
	//**************导出数据信息****************
	//注册统计信息导出表头
	public static final String[] REGISTER_MESSAGE_HEADER = {"用户ID","用户昵称","渠道号","注册时间","注册方式"};
	
	
	//****************************查询和导出时间跨度限制****************************
	
	//查询时间跨度限制最大为一个月
	public static final Integer QUERY_MAX_TIME_SECOND = 31 * 24 * 60 * 60;
	
	public static final String GWSCOD1131 = "1131";
	public static final String GWSMSG1131 = "导出数据时间跨度最大支持一个月!";
	
	// 登录prefix
	public static final String CHANNEL_ADMIN_MANAGE_IS_LOGIN = "channel_admin_manage_is_login";
	public static final Long CHANNEL_ADMIN_MANAGE_IS_LOGIN_TIMEOUT = 10 * 30 * 60L;
	
	
	// 方法名常量
	public static final String METHODNAME = "methodName";
	
}
