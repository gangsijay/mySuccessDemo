package com.two.core.base.jdbc.dao.impl;

import com.two.core.base.common.util.PageQuery;
import com.two.core.base.jdbc.dao.BaseAbstractDao;

/**
 * 功能描述:PostgreSQL分页实现 
 *
 * @Auther: 田杰
 * @Date: 2018/4/14 23:39
 */
public class PostgreJdbcDao extends BaseAbstractDao {
    @Override
    public String prepareSql(String sql, PageQuery pageQuery, int total) {
        if(null== pageQuery) {
            return sql;
        }
        int offset = pageQuery.getRows() * (pageQuery.getPage() - 1);
        StringBuffer sqlBuf = new StringBuffer(50 + sql.length());
        sqlBuf.append(sql);
        sqlBuf.append(" limit ");
        sqlBuf.append(pageQuery.getRows());
        sqlBuf.append(" offset ");
        sqlBuf.append(offset);
        return sqlBuf.toString();
    }
}
