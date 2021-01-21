package com.htl.mapper;

import com.htl.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountMapper {
    //查询所有账户，并获取每个账户所属的用户信息（一对一）
    @Select("select * from account")
    @Results(id = "accountMap",value = {
            /* id=true表示是主键  */
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            @Result(column = "uid",property = "user",one = @One(select = "com.htl.mapper.UserMapper.findById",
                    fetchType = FetchType.EAGER))
    })
    List<Account> findAll();

    //根据id查询账户
    @Select("select * from account where uid=#{userId}")
    List<Account> findAccountByUid(Integer userId);

}
