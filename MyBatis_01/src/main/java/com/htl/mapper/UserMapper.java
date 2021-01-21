package com.htl.mapper;

import com.htl.domain.QueryVo;
import com.htl.domain.User;
import com.htl.domain.User2;

import java.util.List;

public interface UserMapper {
    // 查询所有用户
    List<User> findAll();
    // 保存用户
    void saveUser(User user);
    // 更新用户
    void updateUser(User user);
    // 删除用户（根据用户id）
    void deleteUser(Integer uid);
    // 根据用户id查询用户
    User findById(Integer uid);
    // 根据名称模糊查询用户
    List<User> findByName(String username);
    // 根据名称模糊查询用户（方法2）
    List<User> findByName2(String username);
    // 获取用户的总记录条数
    int findTotal();
    // 根据queryVo中的条件查询用户
    List<User> findUserByVo(QueryVo queryVo);
    // 实体类属性与数据库列名不对应2种解决方式
    List<User2> findAll2();
    List<User2> findAll3();

}

