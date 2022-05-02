package com.akokko.dao;

import com.akokko.pojo.Token;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface TokenMapper extends Mapper<Token> {

    @Delete("delete from tb_cookie where 1 = 1")
    int deleteAll();

}
