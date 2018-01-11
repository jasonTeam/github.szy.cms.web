package com.cms.szy.configuration.annotation;

import java.lang.annotation.*;


/**
 * 
 * (数据过滤) 
 * @ClassName DataFilter 
 * @author ShenZiYang 
 * @date 2018年1月11日 下午6:07:36
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {
	
    /**  表的别名 */
    String tableAlias() default  "";

    /**  true：没有本部门数据权限，也能查询本人数据 */
    boolean user() default true;
}
