package com.two.core.base.common.util;


import java.util.List;

/**
 * 功能描述:
 *
 * @Auther: 田杰
 * @Date: 2018/4/15 00:40
 */
public class Page<T> {
    /**
     * 功能描述:总条数
     *
     * @param:
     * @return:
     * @auther: Tjee
     * @date: 2018/4/15 0:46
     */
    private int total;
    /**
     * 功能描述:查询出来的记录
     *
     * @param:
     * @return:
     * @auther: Tjee
     * @date: 2018/4/15 0:47
     */
    private List<T> rows;

    public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Page() {
    }

    public Page(int total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
