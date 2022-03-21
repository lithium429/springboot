package com.li.frame.spring.dao;

import com.li.frame.spring.model.User;
import io.mybatis.mapper.Mapper;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends Mapper<User,Long> {

    @Select("select * from user limit 1")
    User getOne();
}
