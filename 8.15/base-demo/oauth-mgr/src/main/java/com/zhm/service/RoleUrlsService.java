package com.zhm.service;

import com.zhm.db.RoleUrls;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhm on 17-2-18.
 */
@Mapper
@Service("roleUrlsService")
public interface RoleUrlsService {
    @Select("select * from role_urls where roleid=#{roleid}")
    List<RoleUrls> findByRoleid(@Param("roleid") int roleid);

    @Select("select urlsid from role_urls where roleid=#{roleid}")
    List<Integer> findUrlidByRoleid(int roleid);

    @Delete({"<script>delete from role_urls where roleid=#{roleid} and urlsid in ",
            "<foreach item='item' index='index' collection='urlsids'" ,
                "open='(' separator=',' close=')' >" ,
                "#{item}" ,
            "</foreach></script>"})
    void delByRoleidAndUrlids(@Param("roleid") int roleid, @Param("urlsids") List<Integer> urlsids);

    @Insert("insert into role_urls(client_id,roleid,urlsid,entry_date) values(#{data.client_id},#{data.roleid},#{data.urlsid},#{data.entry_date})")
    void addInfo(@Param("data") RoleUrls rolUrsl);

    @Select("select urlsid from role_urls where roleid=#{roleid} and client_id=#{cid}")
    List<Integer> findUrlidByRoleidAndClientid(@Param("roleid") int roleid, @Param("cid") String cid);

    @Select("select * from role_urls where roleid=#{roleid} and client_id=#{cid}")
    List<RoleUrls> findByRoleidAndClientid(@Param("roleid") int roleid, @Param("cid") String cid);

    @Delete({"<script>delete from role_urls where roleid=#{roleid} and urlsid in ",
            "<foreach item='item' index='index' collection='urlsids'" ,
            "open='(' separator=',' close=')' >" ,
            "#{item}" ,
            "</foreach></script>"})
    void delByRoleidAndUrlidsAndClientid(@Param("roleid") int roleid, @Param("urlsids") List<Integer> removeInfo, @Param("cid") String cid);

    @Delete("delete from role_urls where urlsid=#{id}")
    void delByUrlid(@Param("id") String id);
}
