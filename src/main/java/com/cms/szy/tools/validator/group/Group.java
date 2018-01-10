package com.cms.szy.tools.validator.group;

import javax.validation.GroupSequence;

/**
 * 
 * (定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验) 
 * @ClassName Group 
 * @author ShenZiYang 
 * @date 2018年1月10日 下午9:19:01
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
