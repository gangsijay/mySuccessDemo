package com.zhm.service;

import com.zhm.db.UrlsGroup;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhm on 17-2-18.
 */
@Mapper
@Service("urlsGroupService")
public interface UrlsGroupService {

    @Select("select * from urls_group")
    List<UrlsGroup> findAll();

    @Select("select * from urls_group where ${cond} order by ${sidx} ${sord} limit ${start},${end}")
    List<UrlsGroup> findByCond(@Param("start") int start, @Param("end") int rows, @Param("cond") String cond, @Param("sidx") String sidx, @Param("sord") String sord);

    @Select("select count(id) from urls_group where ${cond}")
    Integer findNumsByCond(@Param("cond") String cond);

    @Insert("insert into urls_group(name,client_id) values(#{data.name},#{data.client_id})")
    void addInfo(@Param("data") UrlsGroup urls);

    @Select("select * from urls_group where id=#{id}")
    UrlsGroup findOne(@Param("id") String id);

    @Update("update urls_group set name=#{data.name} where id=#{data.id}")
    void updateInfo(@Param("data") UrlsGroup urls);

    @Delete("delete from urls_group where id=#{id}")
    void delInfo(@Param("id") String id);

    @Select("select * from urls_group where name=#{name} limit 0,1")
    UrlsGroup findByName(@Param("name") String group_name);

    @Select("select * from urls_group where client_id=#{cid}")
    List<UrlsGroup> findByClientid(@Param("cid") String cid);
}
