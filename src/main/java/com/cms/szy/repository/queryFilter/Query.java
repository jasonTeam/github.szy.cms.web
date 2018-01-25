package com.cms.szy.repository.queryFilter;


import java.util.LinkedHashMap;
import java.util.Map;

import com.cms.szy.tools.xss.SQLFilter;

/**
 * 
 *【查询参数】  
 * @date 2018年1月25日 下午6:20:21
 */
public class Query extends LinkedHashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
	
	//当前页码
    private int pageNo;
    
    //每页条数
    private int pageSize;

    public Query(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        this.pageNo = Integer.parseInt(params.get("pageNo").toString());
        this.pageSize = Integer.parseInt(params.get("pageSize").toString());
        this.put("offset", (pageNo - 1) * pageSize);
        this.put("pageNo", pageNo);
        this.put("pageSize", pageSize);

        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String sidx = params.get("sidx").toString();
        String order = params.get("order").toString();
        this.put("sidx", SQLFilter.sqlInject(sidx));
        this.put("order", SQLFilter.sqlInject(order));
    }

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

    
    
}
