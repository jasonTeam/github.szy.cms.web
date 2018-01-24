package com.cms.szy.tools.result;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *【返回数据】  
 * @date 2018年1月24日 下午2:01:23
 */
public class Ret extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public Ret() {
		put("code", 0);
	}
	
	public static Ret error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static Ret error(String msg) {
		return error(500, msg);
	}
	
	public static Ret error(int code, String msg) {
		Ret r = new Ret();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static Ret ok(String msg) {
		Ret r = new Ret();
		r.put("msg", msg);
		return r;
	}
	
	public static Ret ok(Map<String, Object> map) {
		Ret r = new Ret();
		r.putAll(map);
		return r;
	}
	
	public static Ret ok() {
		return new Ret();
	}

	public Ret put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
