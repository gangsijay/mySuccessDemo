package com.zhm.dao;

import com.zhm.db.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by zhm on 17-3-13.
 */
@Mapper
@Repository("userInfoDao")
public interface UserInfoDao {

    @Select("select * from user_info where mobile=#{username} or email=#{username}")
    UserInfo findByMobileOrEmail(@Param("username") String username);

    @Select("select * from user_info where email=#{email}")
    UserInfo findByEmail(@Param("email") String email);
}
