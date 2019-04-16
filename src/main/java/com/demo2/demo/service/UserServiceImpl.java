package com.demo2.demo.service;

import com.demo2.demo.mapper.UserMapper;
import com.demo2.demo.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 贺威
 * @create 2019-04-12 11:52
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
