package com.htl.test;

import com.htl.entity.Account;
import com.htl.repository.AccountRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test4 {
    public static void main(String[] args) {
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
        Account account = accountRepository.findById(1L);
        System.out.println(account);
        /**
         *  一级缓存：SqlSession 级别，默认开启，不能关闭。一旦关闭，
         *  需要重新实例化sqlSession和accountRepository。
         */
        sqlSession.close();
        sqlSession = sqlSessionFactory.openSession();
        accountRepository = sqlSession.getMapper(AccountRepository.class);
        Account account1 = accountRepository.findById(1L);
        System.out.println(account1);
    }
}

