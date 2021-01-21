package com.htl.mapper;

import com.htl.domain.User;

import java.util.List;

public interface UserMapper {
    //查询所有用户
    List<User> findAll();
    //根据id查询用户
    User findById(Integer uid);
}
