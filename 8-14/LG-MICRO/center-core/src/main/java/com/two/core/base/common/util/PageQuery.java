package com.two.core.base.common.util;

import java.io.Serializable;

/**
 * 功能描述:page 接受参数
 *
 * @Auther: 田杰
 * @Date: 2018/4/14 22:34
 */
public class PageQuery implements Serializable {
    private static final long serialVersionUID = 5665528487834251904L;
    //当前页码
    private int page = 1;
    //每页条数
    private int rows = 10;
    
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
}
