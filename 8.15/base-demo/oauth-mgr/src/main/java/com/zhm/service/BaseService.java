package com.zhm.service;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhm on 17-2-20.
 */
public interface BaseService<T> {

    @Select("select * from ${table_name}")
    List<T> findAll(@Param("table_name") String table_name);

    @Select("select * from ${table_name} where ${cond} order by ${sidx} ${sord} limit ${start},${end}")
    List<T> findByCond(@Param("start") int start, @Param("end") int rows, @Param("cond") String cond, @Param("sidx") String sidx, @Param("sord") String sord, @Param("table_name") String table_name);

    @Select("select count(id) from ${table_name} where ${cond}")
    Integer findNumsByCond(@Param("cond") String cond, @Param("table_name") String table_name);

    @Select("select * from ${table_name} where id=${id}")
    T findById(@Param("id") String id, @Param("table_name") String table_name);

    @Delete("delete from ${table_name} where id=${id}")
    T delById(@Param("id") String id, @Param("table_name") String table_name);
}
