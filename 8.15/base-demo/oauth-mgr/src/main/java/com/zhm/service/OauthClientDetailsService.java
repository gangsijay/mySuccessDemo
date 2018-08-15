package com.zhm.service;

import com.zhm.db.OauthClientDetails;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhm on 17-2-26.
 */
@Mapper
@Service("oauthClientDetailsService")
public interface OauthClientDetailsService {
    @Select("select * from oauth_client_details where client_id=#{cid}")
    OauthClientDetails findByClientid(@Param("cid") String clientId);

    @Select("select t1.* from oauth_client_details t1 left join app_user t2 on t1.client_id=t2.client_id where ${cond} order by ${sidx} ${sord} limit ${start},${end}")
    List<OauthClientDetails> findByCond(@Param("start") int start, @Param("end") int rows, @Param("cond") String cond, @Param("sidx") String sidx, @Param("sord") String sord);

    @Select("select count(t1.client_id) from oauth_client_details   t1 left join app_user t2 on t1.client_id=t2.client_id where ${cond}")
    Integer findNumsByCond(@Param("cond") String cond);


    @Select("select * from oauth_client_details where ${cond} order by ${sidx} ${sord} limit ${start},${end}")
    List<OauthClientDetails> findByAuditCond(@Param("start") int start, @Param("end") int rows, @Param("cond") String cond, @Param("sidx") String sidx, @Param("sord") String sord);

    @Select("select count(client_id) from oauth_client_details  where ${cond}")
    Integer findNumsByAuditCond(@Param("cond") String cond);

    @Update("update oauth_client_details set status=#{status} where client_id=#{client_id}")
    void auditApp(@Param("client_id") String client_id, @Param("status") int status);

    @Update("update oauth_client_details set web_server_redirect_uri=#{data.web_server_redirect_uri} where client_id=#{data.client_id}")
    void updateInfo(@Param("data") OauthClientDetails detail);

    @Delete("delete from oauth_client_details where client_id=#{client_id}")
    void delInfo(@Param("client_id") String client_id);

    @Insert("insert into oauth_client_details(client_id,client_name,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,status,userid,entry_date,autoapprove) values(#{d.client_id},#{d.client_name},#{d.resource_ids},#{d.client_secret},#{d.scope},#{d.authorized_grant_types},#{d.web_server_redirect_uri},#{d.authorities},#{d.access_token_validity},#{d.refresh_token_validity},#{d.additional_information},#{d.status},#{d.userid},#{d.entry_date},#{d.autoapprove})")
    void addInfo(@Param("d") OauthClientDetails info);
}
