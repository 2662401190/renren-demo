package com.demo2.demo.service;

import com.demo2.demo.model.User;

/**
 * @author 贺威
 * @create 2019-04-12 11:50
 */
public interface UserService {

    User findByUsername( String username);
}
