package com.two.core.base.jdbc.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.two.core.base.common.util.Page;
import com.two.core.base.common.util.PageQuery;
import com.two.core.exception.DaoException;

/**
 * 功能描述:基于JDBC提供公共操作接口，对数据库表提供标准的增删改查功能
 *
 * @Auther: 田杰
 * @Date: 2018/4/14 23:12
 */
public interface IJdbcDao {

    /***
     * @Description: 执行sql，通常用于DDL语句，无返回值:
     *             【强制】禁止使用存储过程
     * @Param: [sql]
     * @return: void
     * @Author: 田杰
     * @Date: 2018/4/13
     */
      void execute(String sql) throws DaoException;

    /***
     * @Description: 执行更新SQL语句
     * @Param: [sql]
     * @return: int  返回成功更新的记录数
     * @Author: 田杰
     * @Date: 2018/4/13
     */
      int update(String sql) throws DaoException;

    /***
     * @Description: 执行更新SQL语句
     * @Param: [sql:sql语句, args:占位参数, argTypes:参数类型]
     * @return: int
     * @Author: 田杰
     * @Date: 2018/4/13
     */
      int update(String sql, Object[] args, int[] argTypes) throws DaoException;

    /**
     * 功能描述:
     *
     * @param: [sql, args]
     * @return: int
     * @auther: 田杰
     * @date: 2018/5/23
     */
    int update(String sql, Object... args) throws DaoException;

    /***
     * @Description: 带有对象列表的批量操作
     * @Param: [sql:sql语句, objList:带泛型的List实体对象集合]
     * 例如:update t_actor set first_name = :firstName, last_name = :lastName where id = :id
     * @return: int[]
     * @Author: 田杰
     * @Date: 2018/4/13
     */
      <T> int[] batchUpdate(String sql, List<T> objList) throws DaoException;

    /***
     * @Description: 可设置单次批处理数量的带有对象列表的批量操作
     * @Param: [sql, objList:带泛型的List实体对象集合, batchSize:单次批处理数量, parameterizedPreparedStatementSetter：需实现接口]
     * @return: int[][]
     * @Author: 田杰
     * @Date: 2018/4/13
     */
      <T> int[][] batchUpdate(String sql,
                                   Collection<T> objList,
                                   int batchSize, ParameterizedPreparedStatementSetter<T> parameterizedPreparedStatementSetter) throws DaoException;


    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql]
     * @return: java.lang.String
     * @Author: 田杰
     * @Date: 2018/4/13
     */
      String queryForString(String sql) throws DaoException;

    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql:sql语句, args:占位参数, argTypes:参数类型]
     * @return: java.lang.String
     * @Author: 田杰
     * @Date: 2018/4/13
     */
      String queryForString(String sql, Object[] args, int[] argTypes) throws DaoException;

    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql]
     * @return: int
     * @Author: 田杰
     * @Date: 2018/4/13
     */
      int queryForInt(String sql) throws DaoException;

    /***
     * @Description:执行查询SQL语句
     * @Param: [sql, args, argTypes]
     * @return: int
     * @Author: 田杰
     * @Date: 2018/4/13
     */
      int queryForInt(String sql, Object[] args, int[] argTypes) throws DaoException;

    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql]
     * @return: long
     * @Author: 田杰
     * @Date: 2018/4/13
     */
      long queryForLong(String sql) throws DaoException;

    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql, objClass]
     * @return: T
     * @Author: 田杰
     * @Date: 2018/4/13
     */
      <T> T queryForObject(String sql, Class<T> objClass) throws DaoException;

    /*** 
     * @功能描述: $执行查询SQL语句
     * @Param: [sql, objClass, args] 
     * @return: T
     * @Author: 田杰
     * @Date: 2018/5/10
     */
      <T> T queryForObject(String sql, Class<T> objClass, Object... args) throws DaoException;

    /***
     * @Description: 执行查询SQL语句, 验证该表是否存在
     * @Param: [tableName]
     * @return: boolean
     * @Author: 田杰
     * @Date: 2018/4/13
     */
      boolean tableExist(String tableName) throws DaoException;

    /***
     * @Description: 得到最大Id, 此方法过时，效率不太适合分布式环境运用,不建议使用
     * @Param: [fieldId, 唯一主鍵 数据库字段必须为整形, tableName]
     * @return: long
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    @Deprecated
      long nextId(String fieldId, String tableName) throws DaoException;

    /**
     * 功能描述:获取id 推荐使用，但依赖于时钟，要求高可以搭建id服务器提供id
     *
     * @param:
     * @return:
     * @auther: Tjee
     * @date: 2018/4/14 2:54
     */
      long nextId() throws DaoException;

    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql:sql语句, args:占位参数, argTypes:参数类型,poClass]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
      <T> List<T> queryForList(String sql, Object[] args, int[] argTypes,
                                    Class<T> poClass) throws DaoException;


    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql, poClass]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
      <T> List<T> queryForList(String sql, Class<T> poClass)
            throws DaoException;

    /*** 
     * @功能描述: 执行查询SQL语句
     * @Param: [sql, poClass, args]
     * @return: java.util.List<T> 
     * @Author: 田杰
     * @Date: 2018/5/10
     */
    public <T> List<T> queryForList(String sql, Class<T> poClass, Object... args)
            throws DaoException;

    /**
     * 功能描述:执行查询SQL语句
     *
     * @param: [sql, args, poClass]
     * @return: java.util.List<T>
     * @auther: 田杰
     * @date: 2018/5/28
     */
    public <T> List<T> queryForList(String sql, Object[] args,
                                    Class<T> poClass) throws DaoException;

    /***
     * @Description: 执行查询SQL语句
     * @Param: [sql, args, argTypes, poClass]
     * @return: T
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    public <T> T queryForObject(String sql, Object[] args, int[] argTypes,
                                Class<T> poClass) throws DaoException;

    /*** 
     * @功能描述: 执行查询SQL语句 需自己实现rowMapper
     * @Param: [sql, args, rowMapper]
     * @return: T
     * @Author: 田杰
     * @Date: 2018/5/11
     */
    <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper) throws DaoException;

    /*** 
     * @功能描述: 执行查询SQL语句 需自己实现rowMapper
     * @Param: [sql, rowMapper, args]
     * @return: T
     * @Author: 田杰
     * @Date: 2018/5/11
     */
    <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) throws DaoException;

    /***
     * @Description: 执行分页查询SQL语句
     * @Param: [sql:sql语句, args:占位参数, argTypes:参数类型,poClass]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    public <T> Page<T> queryByPage(String sql, Object[] args, int[] argTypes,
                                   PageQuery pageQuery, Class<T> poClass) throws DaoException;

    /***
     * @Description:执行分页查询SQL语句
     * @Param: [sql, page, poClass]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    public <T> Page<T> queryByPage(String sql, PageQuery pageQuery, Class<T> poClass)
            throws DaoException;

    /***
     * @Description: 执行分页查询SQL语句
     * @Param: [sql, page, param, poClass]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    public <T> Page<T> queryByPage(String sql, PageQuery page, Object[] param, Class<T> poClass)
            throws DaoException;

    /**
     * 功能描述:执行分页查询SQL语句
     *
     * @param:
     * @return:
     * @auther: 田杰
     * @date: 2018/5/10
     */
    public <T> Page<T> queryByPage(String sql, PageQuery pageQuery, Class<T> poClass, Object... args)
            throws DaoException;

    /***
     * @Description: 执行分页查询SQL语句
     * @Param: [sql, args, argTypes, page, mapper]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    public <T> Page<T> queryByPage(String sql, Object[] args, int[] argTypes,
                                   PageQuery pageQuery, RowMapper<T> mapper) throws DaoException;

    /***
     * @Description: 执行分页查询SQL语句
     * @Param: [sql, page, param, mapper]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    public <T> Page<T> queryByPage(String sql, PageQuery pageQuery, Object[] param, RowMapper<T> mapper)
            throws DaoException;

    /***
     * @Description: 执行分页查询SQL语句
     * @Param: [sql, page, param, mapper]
     * @return: java.util.List<T>
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    public <T> Page<T> queryByPage(String sql, PageQuery pageQuery, RowMapper<T> mapper, Object... args)
            throws DaoException;

    /*** 
     * @功能描述: 执行分页查询SQL语句多用于复杂表关联
     * @Param: [sql, pageQuery, args, argTypes]
     * @return: com.cdrundle.common.util.Page<java.lang.Object> 
     * @Author: 田杰
     * @Date: 2018/5/11
     */
    Page queryForListByPage(String sql, PageQuery pageQuery, Object[] args, int[] argTypes)
            throws DaoException;

    /*** 
     * @功能描述: 执行分页查询SQL语句.多用于复杂表关联
     * @Param: [sql, pageQuery, args]
     * @return: com.cdrundle.common.util.Page<java.lang.Object> 
     * @Author: 田杰
     * @Date: 2018/5/11
     */
    Page queryForListByPage(String sql, PageQuery pageQuery, Object... args)
            throws DaoException;

    /***
     * @Description: 执行sql语句 得到记录总数
     * @Param: [sql, args, argTypes]
     * @return: int
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    public int getTotalCount(String sql) throws DaoException;

    /***
     * @Description: 执行sql语句 得到记录总数
     * @Param: [sql, args, argTypes]
     * @return: int
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    public int getTotalCount(String sql, Object[] args, int[] argTypes)
            throws DaoException;

    /***
     * @Description: 执行sql语句 得到记录总数
     * @Param: [sql, param]
     * @return: int
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    public int getTotalCount(String sql, Object[] param) throws DaoException;

    /***
     * @Description: 执行sql语句 得到记录总数
     * @Param: [sql, param]
     * @return: int
     * @Author: 田杰
     * @Date: 2018/4/13
     */
    public int getTotalCount2(String sql, Object... args) throws DaoException;
}
