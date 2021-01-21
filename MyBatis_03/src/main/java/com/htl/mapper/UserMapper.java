package com.htl.mapper;

import com.htl.domain.User2;
import com.htl.domain.User4;

import java.util.List;

public interface UserMapper {
    //查询所有（一对多）
    List<User2> findAll();
    //查询所有[人物对应角色]（多对多）
    List<User4> findAllUserRole();

}

