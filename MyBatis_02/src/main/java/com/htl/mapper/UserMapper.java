package com.htl.mapper;

import com.htl.domain.QueryVo;
import com.htl.domain.User;

import java.util.List;

public interface UserMapper {
    // 查询所有用户
    List<User> findAll();
    // 根据用户id查询用户
    User findById(Integer uid);
    // 根据名称模糊查询用户
    List<User> findByName(String username);
    // 根据queryVo中的条件查询用户
    List<User> findUserByVo(QueryVo queryVo);
    // 根据传入参数条件查询用户。（查询条件有可能是用户名、性别、地址、都有）
    List<User> findUserByCondition(User user);
    // 根据QueryVo中提供的id集合(ids)查询用户
    List<User> findUserInIds(QueryVo queryVo);

}

