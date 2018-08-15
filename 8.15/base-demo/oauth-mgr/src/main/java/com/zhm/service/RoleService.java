package com.zhm.service;

import com.zhm.db.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhm on 17-2-18.
 */
@Mapper
@Service("roleService")
public interface RoleService {
    @Select("select * from role")
    List<Role> findAll();

    @Select("select * from role where ${cond} order by ${sidx} ${sord} limit ${start},${end}")
    List<Role> findByCond(@Param("start") int start, @Param("end") int rows, @Param("cond") String cond, @Param("sidx") String sidx, @Param("sord") String sord);

    @Select("select count(id) from role where ${cond}")
    Integer findNumsByCond(@Param("cond") String cond);

    @Insert("insert into role(name,client_id) values(#{data.name},#{data.client_id})")
    void addInfo(@Param("data") Role urls);

    @Select("select * from role where id=#{id}")
    Role findOne(@Param("id") String id);

    @Update("update role set name=#{data.name} where id=#{data.id}")
    void updateInfo(@Param("data") Role urls);

    @Delete("delete from role where id=#{id}")
    void delInfo(@Param("id") String id);

    @Select("select * from role where client_id=#{cid}")
    List<Role> findByClientid(@Param("cid") String cid);
}
