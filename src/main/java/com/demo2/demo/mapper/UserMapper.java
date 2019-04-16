package com.demo2.demo.mapper;

import com.demo2.demo.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author 贺威
 * @create 2019-04-12 11:48
 */
public interface UserMapper {

    User findByUsername(@Param("#username") String username);
}
