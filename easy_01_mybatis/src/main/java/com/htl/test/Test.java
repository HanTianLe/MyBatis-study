package com.htl.test;

import com.htl.entity.Account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 *      原生接口：AccountMapper.xml。     ——————测试
 *
 *      MyBatis 核心接口和类
 *                  1、SqlSessionFactoryBuilder 类
 *                  2、SqlSessionFactory 接口
 *                  3、SqlSession 接口
 *
 */
public class Test {
    public static void main(String[] args) {
        // 加载MyBatis配置文件。
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        // 获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String statement = "com.htl.mapper.AccountMapper.save";
        Account account = new Account(1L,"韩信","123",100);
        //参数statement代表执行的方法。 account代表插入的数据。
        sqlSession.insert(statement,account);
        // 提交事务。     【一定要记住！！！】
        sqlSession.commit();
        // 关闭sqlSession
        sqlSession.close();
    }
}

