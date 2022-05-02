package com.akokko.dao;

import com.akokko.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserMapper extends Mapper<User> {

    @Select("select * from tb_user where email = #{email}")
    List<User> checkEmail(@Param("email") String email);

    @Update("update tb_user set password = #{password} where email = #{email} and code = #{code}")
    int changePassword(@Param("email") String email, @Param("password") String password, @Param("code") String code);
}
