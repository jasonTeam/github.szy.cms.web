package com.cms.szy.configuration.aspect;


import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cms.szy.configuration.annotation.DataFilter;
import com.cms.szy.entity.po.User;
import com.cms.szy.service.DeptService;
import com.cms.szy.tools.constant.Constant;
import com.cms.szy.tools.exception.RRException;
import com.cms.szy.tools.shiro.ShiroUtils;

/**
 * 
 *【数据过滤，切面处理类】  
 * @date 2018年1月24日 下午4:11:30
 */
@Aspect
@Component
public class DataFilterAspect {
    @Autowired
    private DeptService deptService;

    @Pointcut("@annotation(com.cms.szy.configuration.annotation.DataFilter)")
    public void dataFilterCut() {

    }

    @Before("dataFilterCut()")
    public void dataFilter(JoinPoint point) throws Throwable {
        Object params = point.getArgs()[0];
        if(params != null && params instanceof Map){
            User user = ShiroUtils.getUserEntity();

            //如果不是超级管理员，则只能查询本部门及子部门数据
            if(user.getUserId() != Constant.SUPER_ADMIN){
                Map map = (Map)params;
                map.put("filterSql", getFilterSQL(user, point)); //getFilterSQL(user, point) 最后取出的是sql语句
            }

            return ;
        }

        throw new RRException("要实现数据权限接口的参数，只能是Map类型，且不能为NULL");
    }

    /**
     * 获取数据过滤的SQL
     */
    private String getFilterSQL(User user, JoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        DataFilter dataFilter = signature.getMethod().getAnnotation(DataFilter.class);
        //获取表的别名
        String tableAlias = dataFilter.tableAlias();
        if(StringUtils.isNotBlank(tableAlias)){
            tableAlias +=  ".";
        }

        //获取子部门ID
//       String subDeptIds = deptService.getSubDeptIdList(user.getDeptId());

        StringBuilder filterSql = new StringBuilder(); 
        filterSql.append("and (");
//        filterSql.append(tableAlias).append("dept_id in(").append(subDeptIds).append(")");

        //没有本部门数据权限，也能查询本人数据
        if(dataFilter.user()){
            filterSql.append(" or ").append(tableAlias).append("user_id=").append(user.getUserId());
        }
        filterSql.append(")");

        return filterSql.toString();
    }
}
