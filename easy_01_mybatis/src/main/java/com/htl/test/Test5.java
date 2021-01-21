package com.htl.test;

import com.htl.entity.Account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 *  动态SQL测试类
 *
 */
public class Test5 {
    public static void main(String[] args) {
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Account account = new Account();


        /*account.setId(7L);
        account.setUsername("韩信");
        account.setPassword("123");
        account.setAge(100);
        AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
        Account account1 = accountRepository.findByAccount(account);
        System.out.println(account1);
        sqlSession.close();*/


        /*account.setId(7L);
        account.setUsername("韩信");
        AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
        System.out.println(accountRepository.update2(account));
        sqlSession.commit();
        sqlSession.close();*/


        /*List<Long> ids = new ArrayList<Long>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        account.setIds(ids);
        AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
        System.out.println(accountRepository.findByIds(account));
        sqlSession.close();*/
    }
}

