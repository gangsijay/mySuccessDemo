package com.zhm.service;

import com.zhm.model.AppUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

/**
 * Created by zhm on 17-2-25.
 */
@Mapper
@Service("appUserService")
public interface AppUserService {
    @Select("select count(id) from app_user where client_id=#{cid} and email=#{email}")
    long checkUserAllowVisitApp(@Param("cid") String client_id, @Param("email") String email);

    @Select("select * from app_user where userid=#{userid} and client_id=#{cid}")
    AppUser findByClientAndUserid(@Param("userid") String userid, @Param("cid") String client_id);

    @Insert("insert into app_user(email,client_id,isadmin) values(#{data.email},#{data.client_id},#{data.isadmin})")
    void addInfo(@Param("data") AppUser appUser);

    @Update("update app_user set isadmin=#{data.isadmin} where id=#{data.id}")
    void updateInfo(@Param("data") AppUser dbUser);

    @Delete("delete from app_user where email=#{email} and client_id=#{cid}")
    void delInfo(String email, String cid);
}
