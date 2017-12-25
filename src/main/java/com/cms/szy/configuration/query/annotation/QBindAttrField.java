package com.cms.szy.configuration.query.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.cms.szy.configuration.query.Where;




/**
 * 查询绑定属性
 *
 * @version 
 * @author zengjq  2016年10月28日 下午6:20:57
 * 
 */
@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface QBindAttrField {
	public abstract String fieldName();
	public abstract Where where();
}
