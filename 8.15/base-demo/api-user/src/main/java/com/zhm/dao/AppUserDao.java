package com.zhm.dao;

import com.zhm.db.AppUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by zhm on 17-3-18.
 */
@Mapper
@Repository("appUserDao")
public interface AppUserDao {
    @Insert("insert into app_user(userid,client_id,isadmin) values(#{data.userid},#{data.client_id},#{data.isadmin})")
    void addInfo(@Param("data") AppUser appUser);

}
