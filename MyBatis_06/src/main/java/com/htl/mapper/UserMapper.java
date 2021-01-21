package com.htl.mapper;

import com.htl.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 *  在 MyBatis中，针对 CRUD 一共有四个注解
 *  分别为：@Select、@Insert、@Update、@Delete
 */
public interface UserMapper {
    //查询所有用户
    @Select(value = "select * from user")
    List<User> findAll();

    //保存用户
    @Insert(value = "insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    //更新用户
    @Update(value = "update user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    //删除用户
    @Delete(value = "delete from user where id=#{id}")
    void deleteUser(Integer uid);

    //根据id查询用户
    @Select("select * from user where id=#{id}")
    User findById(Integer uid);

    //根据用户名称模糊查询
    @Select("select * from user where username like #{username}")   //方法一（优）
    //@Select("select * from user where username like '%${value}%'")    //方法二
    List<User> findUserByName(String username);

    //查询总用户数量
    @Select("select count(*) from user")
    int findTotalUser();

}

