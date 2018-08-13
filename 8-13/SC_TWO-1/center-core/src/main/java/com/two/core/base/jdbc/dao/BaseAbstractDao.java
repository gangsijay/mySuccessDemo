package com.two.core.base.jdbc.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import com.two.core.base.common.util.Page;
import com.two.core.base.common.util.PageQuery;
import com.two.core.base.jdbc.id.IdWorker;
import com.two.core.exception.DaoException;

/***
 * @:功能描述: base操作 抽象类
 * @Author: 田杰
 * @Date: 2018/4/13 10:40
 */
public abstract class BaseAbstractDao implements IJdbcDao {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
//    @Autowired
//    protected IdWorker idWorker;

    public BaseAbstractDao() {

    }

    /***
     * @Description: 执行sql，通常用于DDL语句，无返回值:
     *             【强制】禁止使用存储过程
     * @Param: [sql]
     * @return: void
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public void execute(String sql) throws DaoException {
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }


    /***
     * @Description: 执行更新SQL语句
     * @Param: [sql]
     * @return: int  返回成功更新的记录数
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public int update(String sql) throws DaoException {
        try {
            int i = jdbcTemplate.update(sql);
            return i;
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /***
     * @Description: 执行更新SQL语句
     * @Param: [sql:sql语句, args:占位参数, argTypes:参数类型]
     * @return: int
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public int update(String sql, Object[] args, int[] argTypes) throws DaoException {
        try {
            return jdbcTemplate.update(sql, args, argTypes);
        } catch (DataAccessException e) {

            throw DaoException.wrap(e);
        }
    }
    /***
     * @Description: 执行更新SQL语句
     * @Param: [sql:sql语句, args:占位参数, argTypes:参数类型]
     * @return: int
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public int update(String sql, Object... args) throws DaoException{
        try {
            return jdbcTemplate.update(sql, args);
        } catch (DataAccessException e) {

            throw DaoException.wrap(e);
        }
    }
    /***
     * @Description: 带有对象列表的批量操作
     * @Param: [sql:sql语句, objList:带泛型的List实体对象集合]
     * 例如:update t_actor set first_name = :firstName, last_name = :lastName where id = :id
     * @return: int[]
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public <T> int[] batchUpdate(String sql, List<T> objList) throws DaoException {
        try {
            return namedParameterJdbcTemplate.batchUpdate(sql,
                    SqlParameterSourceUtils.createBatch(objList.toArray()));
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }

    }

    /***
     * @Description: 可设置单次批处理数量的带有对象列表的批量操作
     * @Param: [sql, objList:带泛型的List实体对象集合, batchSize:单次批处理数量, parameterizedPreparedStatementSetter：需实现接口]
     * @return: int[][]
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public <T> int[][] batchUpdate(String sql, Collection<T> objList, int batchSize, ParameterizedPreparedStatementSetter<T> parameterizedPreparedStatementSetter) throws DaoException {
        try {
            return jdbcTemplate.batchUpdate(sql, objList, batchSize, parameterizedPreparedStatementSetter);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }


    }


    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql]
     * @return: java.lang.String
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public String queryForString(String sql) throws DaoException {
        try {
            return (String) jdbcTemplate.queryForObject(sql, String.class);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql:sql语句, args:占位参数, argTypes:参数类型]
     * @return: java.lang.String
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public String queryForString(String sql, Object[] args, int[] argTypes) throws DaoException {
        try {
            return (String) jdbcTemplate.queryForObject(sql, args, argTypes, String.class);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql]
     * @return: int
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public int queryForInt(String sql) throws DaoException {
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /***
     * @Description:执行查询SQL语句
     * @Param: [sql, args, argTypes]
     * @return: int
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public int queryForInt(String sql, Object[] args, int[] argTypes) throws DaoException {
        try {
            int i = jdbcTemplate.queryForObject(sql, args, argTypes, Integer.class);
            return i;
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql]
     * @return: long
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public long queryForLong(String sql) throws DaoException {
        try {
            return jdbcTemplate.queryForObject(sql, Long.class);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql, objClass]
     * @return: T
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public <T> T queryForObject(String sql, Class<T> objClass) throws DaoException {
        try {
            T r = null;
            try {
                r = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<T>(objClass));
            } catch (EmptyResultDataAccessException e) {
                throw DaoException.wrap(e);
            }
            return r;
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /***
     * @功能描述: 执行查询SQL语句
     * @Param: [sql, objClass, args]
     * @return: T
     * @Author: 田杰
     * @Date: 2018/5/10
     */
    @Override
    public <T> T queryForObject(String sql, Class<T> objClass, Object... args) throws DaoException {
        try {
            T r  = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(objClass), args);
            return r;
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }
    /*** 
     * @功能描述: 执行查询SQL语句
     * @Param: [sql, args, rowMapper]
     * @return: T
     * @Author: 田杰
     * @Date: 2018/5/11
     */
    @Override
    public <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper) throws DaoException {
        try {
            return jdbcTemplate.queryForObject(sql, args,rowMapper);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /*** 
     * @功能描述: 执行查询SQL语句
     * @Param: [sql, rowMapper, args]
     * @return: T
     * @Author: 田杰
     * @Date: 2018/5/11
     */
    @Override
    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) throws DaoException {
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper,args);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }
    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql, args, argTypes, poClass]
     * @return: T
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public <T> T queryForObject(String sql, Object[] args, int[] argTypes,
                                Class<T> poClass) throws DaoException {

        try {
            return jdbcTemplate.queryForObject(sql, args, argTypes, new BeanPropertyRowMapper<T>(poClass));
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /***
     * @Description: 执行查询SQL语句, 验证该表是否存在
     * @Param: [tableName]
     * @return: boolean
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public boolean tableExist(String tableName) throws DaoException {
        StringBuilder sql = new StringBuilder(50);
        sql.append("SELECT 1 FROM TAB WHERE UPPER(TNAME)=UPPER('");
        sql.append(tableName).append("')");
        List<?> list = jdbcTemplate.queryForList(sql.toString());
        return (list != null && !list.isEmpty());
    }


    /***
     * @Description: 得到最大Id, 此方法过时，效率不太适合分布式环境运用,不建议使用
     * @Param: [fieldId, 唯一主鍵 数据库字段必须为整形, tableName]
     * @return: long
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Deprecated
    @Override
    public long nextId(String fieldId, String tableName) throws DaoException {
        StringBuilder sql = new StringBuilder();
        sql.append("select  case when max(" + fieldId + ") is null  then 0 else max(" + fieldId + ")END  maxId from ")
                .append(tableName);
        long id = 0L;
        try {
            id = jdbcTemplate.queryForObject(sql.toString(),
                    Long.class);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
        id = id + 1;
        return id;
    }

    /**
     * 功能描述:获取id 推荐使用，但依赖于时钟，要求高可以搭建id服务器提供id
     *
     * @param:
     * @return:
     * @auther: Tjee
     * @date: 2018/4/14 2:54
     */
    @Override
    public long nextId() throws DaoException {
        return 0l;//idWorker.nextId();
    }

    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql:sql语句, args:占位参数, argTypes:参数类型,poClass]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public <T> List<T> queryForList(String sql, Object[] args, int[] argTypes,
                                    Class<T> poClass) throws DaoException {
        try {
            return jdbcTemplate.query(sql, args, argTypes, new BeanPropertyRowMapper<T>(poClass));
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /**
     * 功能描述:执行查询SQL语句
     *
     * @param: [sql, args, poClass]
     * @return: java.util.List<T>
     * @auther: 田杰
     * @date: 2018/5/28
     */
    @Override
    public <T> List<T> queryForList(String sql, Object[] args,
                                    Class<T> poClass) throws DaoException {
        try {
            return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper(poClass));
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }
    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql, poClass]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public <T> List<T> queryForList(String sql, Class<T> poClass)
            throws DaoException {
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(poClass));
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /***
     * @功能描述: 执行查询SQL语句
     * @Param: [sql, poClass, args]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/5/10
     */
    @Override
    public <T> List<T> queryForList(String sql, Class<T> poClass, Object... args)
            throws DaoException {
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(poClass), args);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /***
     * @Description: 执行分页查询SQL语句
     * @Param: [sql:sql语句, args:占位参数, argTypes:参数类型,poClass]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public <T> Page<T> queryByPage(String sql, Object[] args, int[] argTypes,
                                   PageQuery pageQuery, Class<T> poClass) throws DaoException {
        try {
            return queryByPage(sql, args, argTypes, pageQuery, new BeanPropertyRowMapper<T>(poClass));
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }

    }

    /***
     * @Description: 执行分页查询SQL语句
     * @Param: [sql, page, poClass]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public <T> Page<T> queryByPage(String sql, PageQuery pageQuery, Class<T> poClass)
            throws DaoException {
        try {
            int total = getTotalCount(sql);
            List<T> list = jdbcTemplate.query(prepareSql(sql, pageQuery, total), new BeanPropertyRowMapper<T>(poClass));
            return new Page<>(total, list);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }

    }

    /***
     * @Description: 执行分页查询SQL语句, 自动映射
     * @Param: [sql, page, param, poClass]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public <T> Page<T> queryByPage(String sql, PageQuery pageQuery, Object[] param, Class<T> poClass)
            throws DaoException {
        try {
            int total = getTotalCount(sql, param);
            List<T> list = jdbcTemplate.query(prepareSql(sql, pageQuery, total), param, new BeanPropertyRowMapper<T>(poClass));
            return new Page<>(total, list);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }

    }

    @Override
    public <T> Page<T> queryByPage(String sql, PageQuery pageQuery, Class<T> poClass, Object... args)
            throws DaoException {
        try {
            int total = getTotalCount2(sql, args);
            List<T> list = jdbcTemplate.query(prepareSql(sql, pageQuery, total), new BeanPropertyRowMapper<T>(poClass), args);
            return new Page<>(total, list);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }

    }

    /***
     * @Description: 执行分页查询SQL语句
     * @Param: [sql, args, argTypes, page, mapper]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public <T> Page<T> queryByPage(String sql, Object[] args, int[] argTypes,
                                   PageQuery pageQuery, RowMapper<T> mapper) throws DaoException {
        try {
            int total = getTotalCount(sql, args, argTypes);
            List<T> list = jdbcTemplate.query(prepareSql(sql, pageQuery, total), args, argTypes, mapper);
            return new Page<>(total, list);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /***
     * @Description: 执行分页查询SQL语句
     * @Param: [sql, page, param, mapper]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public <T> Page<T> queryByPage(String sql, PageQuery pageQuery, Object[] param, RowMapper<T> mapper)
            throws DaoException {
        try {

            int total = getTotalCount(sql, param);
            List<T> list = jdbcTemplate.query(prepareSql(sql, pageQuery, total), param, mapper);
            return new Page<>(total, list);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /***
     * @功能描述:执行分页查询SQL语句
     * @Param: [sql, pageQuery, mapper, args] 
     * @return: com.cdrundle.common.util.Page<T> 
     * @Author: 田杰
     * @Date: 2018/5/11
     */
    @Override
    public <T> Page<T> queryByPage(String sql, PageQuery pageQuery, RowMapper<T> mapper, Object... args)
            throws DaoException {
        try {

            int total = getTotalCount2(sql, args);
            List<T> list = jdbcTemplate.query(prepareSql(sql, pageQuery, total), mapper, args);
            return new Page<>(total, list);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /***
     * @功能描述:  执行分页查询SQL语句.多用于复杂表关联
     * @Param: [sql, pageQuery, args]
     * @return: com.cdrundle.common.util.Page<java.lang.Object>
     * @Author: 田杰
     * @Date: 2018/5/11
     */
    @Override
    public Page queryForListByPage(String sql, PageQuery pageQuery, Object... args)
            throws DaoException {
        try {
            int total = getTotalCount2(sql, args);
            List<Map<String, Object>> list = jdbcTemplate.queryForList(prepareSql(sql, pageQuery, total), args);
            return new Page(total, list);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }
    }

    /*** 
     * @功能描述: 执行分页查询SQL语句.多用于复杂表关联
     * @Param: [sql, pageQuery, args, argTypes] 
     * @return: com.cdrundle.common.util.Page<java.lang.Object> 
     * @Author: 田杰
     * @Date: 2018/5/11
     */
    @Override
    public Page  queryForListByPage(String sql, PageQuery pageQuery, Object[] args, int[] argTypes)
            throws DaoException {
        try {
            int total = getTotalCount(sql, args);
            List<Map<String, Object>> list = jdbcTemplate.queryForList(prepareSql(sql, pageQuery, total), args, argTypes);
            return new Page(total, list);
        } catch (DataAccessException e) {
            throw DaoException.wrap(e);
        }

    }

    /***
     * @Description: 执行sql语句 得到记录总数
     * @Param: [sql, param]
     * @return: int
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public int getTotalCount(String sql) throws DaoException {
        StringBuffer countSql = new StringBuffer(30 + sql.length());
        countSql.append(" SELECT COUNT(*) FROM (").append(sql).append(") Z");
        return jdbcTemplate.queryForObject(countSql.toString(), Integer.class);
    }

    /***
     * @Description: 执行sql语句 得到记录总数
     * @Param: [sql, args, argTypes]
     * @return: int
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public int getTotalCount(String sql, Object[] args, int[] argTypes)
            throws DaoException {
        StringBuffer countSql = new StringBuffer(30 + sql.length());
        countSql.append(" SELECT COUNT(*) FROM (").append(sql).append(") Z");

        return jdbcTemplate.queryForObject(countSql.toString(), args,
                argTypes, Integer.class);
    }

    /***
     * @Description: 执行sql语句 得到记录总数
     * @Param: [sql, param]
     * @return: int
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Override
    public int getTotalCount(String sql, Object[] param) throws DaoException {
        StringBuffer countSql = new StringBuffer(30 + sql.length());
        countSql.append(" SELECT COUNT(*) FROM (").append(sql).append(") Z");
        return jdbcTemplate.queryForObject(countSql.toString(), param, Integer.class);
    }

    @Override
    public int getTotalCount2(String sql, Object... args) throws DaoException {
        StringBuffer countSql = new StringBuffer(30 + sql.length());
        countSql.append(" SELECT COUNT(*) FROM (").append(sql).append(") Z");
        return jdbcTemplate.queryForObject(countSql.toString(), Integer.class, args);
    }

    public abstract String prepareSql(String sql, PageQuery pageQuery, int total);


}
