package com.htl.test;

import com.htl.domain.Account;
import com.htl.domain.User;
import com.htl.mapper.AccountMapper;
import com.htl.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest1 {
    /**
     * 由于测试众多，优化代代码如下：
     */
    private InputStream resourceAsStream;       //输入流对象
    private SqlSessionFactory sqlSessionFactory;//SqlSessionFactory工厂对象
    private SqlSession sqlSession;              //sqlSession对象
    private UserMapper userMapper;              //代理对象
    private AccountMapper accountMapper;        //代理对象

    @Before // 在测试方法执行之前执行
    public void init() throws IOException {
        //1、读取配置文件
        resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2、创建SqlSessionFactory工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、用工厂生产sqlSession对象
        sqlSession = sqlSessionFactory.openSession(true);
        //4、使用sqlSession创建接口代理对象（mapper）
        accountMapper = sqlSession.getMapper(AccountMapper.class);
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After // 在测试方法执行之后执行
    public void destroy() throws IOException {
        //提交事务（如果openSession已设置自动提交则不需要）
        //sqlSession.commit();
        //释放资源
        sqlSession.close();
        resourceAsStream.close();
    }

    //一对一【实现延迟加载】
    @Test
    public void testFindAllAccount(){
        List<Account> accounts = accountMapper.findAll();
//        for (Account account : accounts){
//            System.out.println("---------------------每个account的信息------------------");
//            System.out.println(account);
//            System.out.println(account.getUser());
//        }
    }

    //一对多【实现延迟加载】
    @Test
    public void testFindAllUser(){
        List<User> users = userMapper.findAll();
//        for (User user : users){
//            System.out.println("---------------------每个user的信息------------------");
//            System.out.println(user);
//            System.out.println(user.getAccounts());
//        }
    }


}

