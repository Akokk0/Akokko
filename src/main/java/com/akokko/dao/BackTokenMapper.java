package com.akokko.dao;

import com.akokko.pojo.BackToken;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface BackTokenMapper extends Mapper<BackToken> {

    @Delete("delete from tb_backCookie where 1 = 1")
    int deleteAll();

}
