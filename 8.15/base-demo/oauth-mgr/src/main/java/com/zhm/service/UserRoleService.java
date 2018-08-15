package com.zhm.service;

import com.zhm.db.UserRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhm on 17-2-19.
 */
@Mapper
@Service("userRoleService")
public interface UserRoleService {
    @Select("select * from user_role where userid=#{userid}")
    List<UserRole> findByUserid(@Param("userid") String userid);

    @Select("select roleid from user_role where userid=#{userid}")
    List<Integer> findRoleidByUserid(@Param("userid") String userid);

    @Insert("insert into user_role(userid,roleid,entry_date,client_id) values(#{data.userid},#{data.roleid},#{data.entry_date},#{data.client_id})")
    void addInfo(@Param("data") UserRole info);

    @Delete({"<script>delete from user_role where userid=#{userid} and roleid in ",
            "<foreach item='item' index='index' collection='roleids'" ,
            "open='(' separator=',' close=')' >" ,
            "#{item}" ,
            "</foreach></script>"})
    void delByUseridAndRoleids(@Param("userid") String userid, @Param("roleids") List<Integer> roleids);

    @Select("select * from user_role where userid=#{userid} and client_id=#{cid}")
    List<UserRole> findByUseridAndClientid(@Param("userid") String userid, @Param("cid") String cid);

    @Select("select roleid from user_role where userid=#{userid} and client_id=#{cid}")
    List<Integer> findRoleidByUseridAndClientid(@Param("userid") String userid, @Param("cid") String cid);
    @Delete({"<script>delete from user_role where userid=#{userid} and client_id=#{cid} and roleid in ",
            "<foreach item='item' index='index' collection='roleids'" ,
            "open='(' separator=',' close=')' >" ,
            "#{item}" ,
            "</foreach></script>"})
    void delByUseridAndRoleidsAncClientid(@Param("userid") String userid, @Param("roleids") List<Integer> roleids, @Param("cid") String cid);
}
