package com.htl.mapper;

import com.htl.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@CacheNamespace(blocking = true)
public interface UserMapper {
    //查询所有用户，并查出其所有的账户（一对多）
    @Select(value = "select * from user")
    @Results(id = "userMap",value = {
            @Result(id = true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "birthday",property = "userBirthday"),
            @Result(column = "id",property = "accounts",many = @Many(select = "com.htl.mapper.AccountMapper.findAccountByUid",
                    fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    //根据id查询用户
    @Select("select * from user where id=#{id}")
    @ResultMap(value = {"userMap"})
    User findById(Integer uid);

}

