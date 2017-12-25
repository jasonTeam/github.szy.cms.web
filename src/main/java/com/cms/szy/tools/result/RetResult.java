package com.cms.szy.tools.result;

/**
 * 
 * (请求响应结果) 
 * @author ShenZiYang 
 * @date 2017年12月5日 下午10:41:02
 */
public class RetResult {
	
	/*** 返回码*/
	private String code;
	
	/*** 返回信息*/
	private String message;
	
	/*** 返回数据*/
	private Object data;
	
	/*** 附加数据*/
	private Object extraData;
	
	/**
	 * 设置返回结果
	 * @param retResult
	 * @param code
	 * @param message
	 * @param object
	 * @return
	 */
	public static RetResult setRetDate(String code, String message, Object object) {
		RetResult retResult = new RetResult();
		retResult.setCode(code);
		retResult.setMessage(message);
		retResult.setData(null == object ? "" : object);
		return retResult;
	}
	
	/**
	 * 
	 * (设置返回结果) 
	 * @param code
	 * @param message
	 * @param object
	 * @param extraObject
	 * @return RetResult返回类型   
	 * @author ShenZiYang
	 * @date 2017年12月5日下午10:48:47
	 * @throws
	 */
	public static RetResult setRetDate(String code, String message, Object object, Object extraObject) {
		RetResult retResult = new RetResult();
		retResult.setCode(code);
		retResult.setMessage(message);
		retResult.setData(null == object ? "" : object);
		retResult.setExtraData(null == extraObject ? "" : extraObject);
		return retResult;
	}
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getExtraData() {
		return extraData;
	}

	public void setExtraData(Object extraData) {
		this.extraData = extraData;
	}
	
}
