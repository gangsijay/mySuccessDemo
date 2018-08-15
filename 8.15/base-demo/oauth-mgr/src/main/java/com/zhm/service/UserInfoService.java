package com.zhm.service;

import com.zhm.db.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhm on 17-2-17.
 */
@Mapper
@Service("userInfoService")
public interface UserInfoService {

    @Select("select * from user_info where email=#{username}")
    UserInfo findByEmail(@Param("username") String username);

    @Select("select * from user_info order by ${sidx} ${sord}")
    List<UserInfo> findByNoPage(@Param("sidx") String sidx, @Param("sord") String sord);

    @Select("select * from user_info where userid=#{userid}")
    UserInfo findByUserid(@Param("userid") String userid);

    @Insert("insert into user_info(userid, username, email) values(#{data.userid}, #{data.username}, #{data.email})")
    void addInfo(@Param("data") UserInfo userInfo);

    @Select("select * from user_info where userid=#{userid}")
    UserInfo findOne(@Param("userid") String userid);

    @Update("update user_info set username=#{data.username}, email=#{data.email},isadmin=#{data.isadmin}  where userid=#{data.userid}")
    void updateInfo(@Param("data") UserInfo userInfo);

    @Delete("delete from user_info where userid=#{userid}")
    void delInfo(@Param("userid") String id);

    @Select("select * from user_info")
    List<UserInfo> findAll();

    @Select("select t1.userid as userid,t1.username as username,t1.email as email,t1.id as id,t1.entry_date as entry_date,t2.isadmin as isadmin,t2.client_id as client_id from app_user t2 left join user_info t1 on t1.userid=t2.userid where ${cond} order by ${sidx} ${sord} limit ${start},${end}")
    List<UserInfo> findByCond(@Param("start") int start, @Param("end") int rows, @Param("cond") String cond, @Param("sidx") String sidx, @Param("sord") String sord);

    @Select("select count(t1.id) from app_user t2 left join user_info t1 on t1.userid=t2.userid where ${cond}")
    Integer findNumsByCond(@Param("cond") String cond);

    @Select("select * from user_info where id=${id}")
    UserInfo findById(@Param("id") String id);

    @Delete("delete from user_info where id=${id}")
    UserInfo delById(@Param("id") String id);

    @Select("select t1.userid as userid,t1.username as username,t1.email as email,t1.mobile as mobile,t1.id as id from user_info t1 where ${cond} order by ${sidx} ${sord} limit ${start},${end} ")
    List<UserInfo> findAllUserByCond(@Param("start") int start, @Param("end") int rows, @Param("cond") String cond, @Param("sidx") String sidx, @Param("sord") String sord);

    @Select("select count(t1.userid) from user_info t1 where ${cond}")
    Integer findAllNumsByCond(@Param("cond")String cond);
}
