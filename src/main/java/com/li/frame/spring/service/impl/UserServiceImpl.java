package com.li.frame.spring.service.impl;

import com.li.frame.spring.dao.UserMapper;
import com.li.frame.spring.model.User;
import com.li.frame.spring.service.UserService;
import io.mybatis.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService<User,Long,UserMapper> implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getOne() {
        return userMapper.getOne();
    }
}
