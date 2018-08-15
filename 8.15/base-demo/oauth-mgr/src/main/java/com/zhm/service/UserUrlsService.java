package com.zhm.service;

import com.zhm.db.UserUrls;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhm on 17-2-18.
 */
@Mapper
@Service("userUrlsService")
public interface UserUrlsService {
    @Select("select * from user_urls where userid=#{userid}")
    List<UserUrls> findByUserid(@Param("userid") String userid);

    @Select("select urlsid from user_urls where userid=#{userid}")
    List<Integer> findUrlidByUserid(@Param("userid") String userid);

    @Delete({"<script>delete from user_urls where userid=#{userid} and urlsid in ",
            "<foreach item='item' index='index' collection='urlsids'" ,
                "open='(' separator=',' close=')' >" ,
                "#{item}" ,
            "</foreach></script>"})
    void delByUseridAndUrlids(@Param("userid") String userid, @Param("urlsids") List<Integer> urlsids);

    @Insert("insert into user_urls(userid,urlsid,entry_date,client_id) values(#{data.userid},#{data.urlsid},#{data.entry_date},#{data.client_id})")
    void addInfo(@Param("data") UserUrls UserUrls);

    @Select("select * from user_urls where userid=#{userid} and client_id=#{cid}")
    List<UserUrls> findByUseridClientid(@Param("userid") String userid, @Param("cid") String cid);

    @Select("select urlsid from user_urls where userid=#{userid} and client_id=#{cid}")
    List<Integer> findUrlidByUseridAndClientid(@Param("userid") String userid, @Param("cid") String cid);

    @Delete({"<script>delete from user_urls where userid=#{userid} and client_id=#{cid} and urlsid in ",
            "<foreach item='item' index='index' collection='urlsids'" ,
            "open='(' separator=',' close=')' >" ,
            "#{item}" ,
            "</foreach></script>"})
    void delByUseridAndUrlidsAndClientid(@Param("userid") String userid, @Param("urlsids") List<Integer> urlsids, @Param("cid") String cid);

    @Delete("delete from user_urls where urlsid=#{id}")
    void delByUrlid(@Param("id") String id);
}
