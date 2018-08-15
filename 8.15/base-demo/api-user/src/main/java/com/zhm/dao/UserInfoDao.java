package com.zhm.dao;

import com.zhm.db.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by zhm on 17-3-14.
 */
@Mapper
@Repository("userInfoDao")
public interface UserInfoDao {

    @Select("select * from user_info where mobile=#{mobile}")
    UserInfo findByMobile(@Param("mobile") String mobile);

    @Insert("insert into user_info(userid,username,password,mobile,status,email,entry_date,last_login,gender,avatar,invite_mobile) values(#{data.userid},#{data.username},#{data.password},#{data.mobile},#{data.status},#{data.email},#{data.entry_date},#{data.last_login},#{data.gender},#{data.avatar},#{data.invite_mobile})")
    void addUser(@Param("data") UserInfo uinfo);

    @Update("update user_info set username=#{data.username},email=#{data.email},gender=#{data.gender},avatar=#{data.avatar},area=#{data.area},age=#{data.age},has_certificate=#{data.has_certificate},driver_type=#{data.driver_type},ID_Num=#{data.ID_Num} where id=#{data.id}")
    void updateUser(@Param("data") UserInfo uinfo);

    @Select("select * from user_info where id=#{id}")
    UserInfo findById(@Param("id") Integer id);

    @Select("select * from user_info where userid=#{userid}")
    UserInfo findByUserid(@Param("userid") String userid);

    @Update("update user_info set password=#{password} where userid=#{userid}")
    void updateUserPassword(@Param("userid") String userid, @Param("password") String password);

    @Select("select count(id) from user_info where mobile=#{mobile}")
    int checkMobileExists(@Param("mobile") String mobile);

    @Select("select count(id) from user_info where email=#{email}")
    int checkEmailExists(@Param("email")String email);

    @Select("select count(id) from user_info where username=#{username}")
    int checkUsernameExists(@Param("username")String username);
}
