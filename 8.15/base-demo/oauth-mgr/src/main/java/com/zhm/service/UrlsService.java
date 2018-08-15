package com.zhm.service;

import com.zhm.db.Urls;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhm on 17-2-17.
 */
@Mapper
@Service("urlsService")
public interface UrlsService {

    @Select("select * from (select t1.id as id,t1.name as name,t1.is_menu as is_menu,t1.need_permission as need_permission,t1.link_url as link_url,t1.entry_date as entry_date,t1.group_id as group_id,t2.name as group_name,t1.client_id as client_id from urls t1 left join urls_group t2 on t1.group_id=t2.id) t3 where ${cond} order by ${sidx} ${sord} limit ${start},${end}")
    List<Urls> findByCond(@Param("start") int start, @Param("end") int rows, @Param("cond") String cond, @Param("sidx") String sidx, @Param("sord") String sord);

    @Select("select count(id) from urls where ${cond}")
    Integer findNumsByCond(@Param("cond") String cond);

    @Insert("insert into urls(name,link_url,group_id,is_menu,need_permission,entry_date,client_id) values(#{data.name},#{data.link_url},#{data.group_id},#{data.is_menu},#{data.need_permission},#{data.entry_date},#{data.client_id})")
    void addInfo(@Param("data") Urls urls);

    @Select("select * from urls where id=#{id}")
    Urls findOne(@Param("id") String id);

    @Update("update urls set name=#{data.name},group_id=#{data.group_id},link_url=#{data.link_url},is_menu=#{data.is_menu},need_permission=#{data.need_permission} where id=#{data.id}")
    void updateInfo(@Param("data") Urls urls);

    @Select("select * from urls where group_id=#{group_id}")
    List<Urls> findByGroupid(@Param("group_id") Integer group_id);

    @Select("select t3.* from (select t1.urlsid " +
            " from role_urls t1 join user_role t2 " +
            " on t1.roleid=t2.roleid " +
            " where t2.userid=#{userid}) t4 join urls t3 on t4.urlsid=t3.id")
    List<Urls> findUrlByUserRole(@Param("userid") String userid);

    @Select("select t1.* from user_urls t2 join urls t1 " +
            " on t1.id=t2.urlsid where t2.userid=#{userid}")
    List<Urls> findUrlsByUser(@Param("userid") String userid);

    @Select("select * from urls")
    List<Urls> findAll();

    @Select("select * from urls where client_id=#{cid}")
    List<Urls> findAllByClientid(@Param("cid") String clientId);

    @Select("select t3.* from (select t1.urlsid " +
            " from role_urls t1 join user_role t2 " +
            " on t1.roleid=t2.roleid " +
            " where t2.userid=#{userid} and t2.client_id=#{cid}) t4 join urls t3 on t4.urlsid=t3.id")
    List<Urls> findUrlByUserRoleAndClientid(@Param("userid") String userid, @Param("cid") String clientId);

    @Select("select t1.* from user_urls t2 join urls t1 " +
            " on t1.id=t2.urlsid where t2.userid=#{userid} and t2.client_id=#{cid}")
    List<Urls> findUrlsByUserAndClientid(@Param("userid") String userid, @Param("cid") String clientId);

    @Select("select * from urls where group_id=#{group_id} and client_id=#{cid}")
    List<Urls> findByGroupidAndClientid(@Param("group_id") Integer id, @Param("cid") String cid);

    @Delete("delete from urls where id=#{id}")
    void delById(@Param("id") String id);
}
