package com.htl.mapper;

import com.htl.domain.Account;
import com.htl.domain.AccountUser;

import java.util.List;

public interface AccountMapper {
    //查询所有账户，并且带有用户名称和地址信息
    List<AccountUser> findAllAccount();
    //查询所有（一对一）
    List<Account> findAll();

}
