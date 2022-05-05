package com.akokko.dao;

import com.akokko.pojo.BackUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface BackUserMapper extends Mapper<BackUser> {

    @Select("select * from tb_backUser where name = #{name} and password = #{password}")
    BackUser backUserLogin(@Param("name") String name, @Param("password") String password);

}
