package com.htl.mapper;

import com.htl.domain.Role;
import com.htl.domain.Role3;

import java.util.List;

public interface RoleMapper {
    //查询所有角色
    List<Role> findAll();
    //查询所有[角色对应人物]（多对多）
    List<Role3> findAllRoleUser();

}
