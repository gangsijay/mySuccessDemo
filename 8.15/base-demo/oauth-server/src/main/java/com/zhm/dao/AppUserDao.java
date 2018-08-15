package com.zhm.dao;

import com.zhm.db.AppUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhm on 17-3-13.
 */
@Mapper
@Repository("appUserDao")
public interface AppUserDao {
    @Select("select count(id) from app_user where client_id=#{cid} and userid=#{userid}")
    long checkUserAllowVisitApp(@Param("cid") String clientId, @Param("userid") String userid);

    @Select("select * from app_user where userid=#{userid}")
    List<AppUser> findListByUserid(@Param("userid") String userid);
}
