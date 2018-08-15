package com.zhm.dao;

import com.zhm.db.OauthClientDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by zhm on 17-3-13.
 */
@Mapper
@Repository("oauthClientDetailsDao")
public interface OauthClientDetailsDao {
    @Select("select * from oauth_client_details where client_id=#{cid}")
    OauthClientDetails findByClientid(@Param("cid") String clientId);
}
