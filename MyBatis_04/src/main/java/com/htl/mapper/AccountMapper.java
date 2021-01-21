package com.htl.mapper;

import com.htl.domain.Account;

import java.util.List;

public interface AccountMapper {
    //查询所有账户
    List<Account> findAll();
    //根据用户id查询账户信息
    List<Account> findAccountByUid(Integer uid);

}
