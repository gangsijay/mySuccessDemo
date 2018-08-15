package com.zhm.dao;

import com.zhm.db.Urls;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhm on 17-3-13.
 */
@Mapper
@Repository("urlsDao")
public interface UrlsDao {


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
}
