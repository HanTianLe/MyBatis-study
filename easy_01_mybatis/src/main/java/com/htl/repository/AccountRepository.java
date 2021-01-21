package com.htl.repository;

import com.htl.entity.Account;

import java.util.List;

public interface AccountRepository {
    public int save(Account account);
    public int update(Account account);
    public int deleteById(long id);
    public List<Account> findAll();
    public Account findById(long id);
    public Account findByName(String name);
    public Account findById2(Long id);
    public Account findByNameAndAge(String name, int age);
    public int count();                     //基本数据类型，统计 Account 总数
    public Integer count2();                //包装类，统计 Account 总数
    public String findNameById(long id);    //String 类型，通过 id 查询 Account 的 name
    public Account findByAccount(Account account);
    public int update2(Account account);
    public List<Account> findByIds(Account account);
}

