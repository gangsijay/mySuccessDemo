package com.two.core.base.jdbc.dao.impl;

import com.two.core.base.common.util.PageQuery;
import com.two.core.base.jdbc.dao.BaseAbstractDao;

/**
 * 功能描述:Mysql分页实现
 *
 * @Auther: 田杰
 * @Date: 2018/4/15 01:52
 */
public class MysqlJdbcDao extends BaseAbstractDao{
    @Override
    public String prepareSql(String sql, PageQuery pageQuery, int total) {
        if(null== pageQuery) {
            return sql;
        }
        int offset = pageQuery.getRows() * (pageQuery.getPage() - 1);
        StringBuffer sqlBuf = new StringBuffer(50 + sql.length());
        sqlBuf.append(sql);
        sqlBuf.append(" limit ");
        sqlBuf.append(offset);
        sqlBuf.append(",");
        sqlBuf.append(pageQuery.getRows());
        return sqlBuf.toString();
    }
}
