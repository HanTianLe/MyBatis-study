package com.htl.test;

import com.htl.domain.Account;
import com.htl.domain.AccountUser;
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
        sqlSession = sqlSessionFactory.openSession();
        //4、使用sqlSession创建接口代理对象（mapper）
        accountMapper = sqlSession.getMapper(AccountMapper.class);
    }

    @After // 在测试方法执行之后执行
    public void destroy() throws IOException {
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        resourceAsStream.close();
    }

    //查询所有账户，并且带有用户名称和地址信息
    @Test
    public void testFindAllAccount(){
        List<AccountUser> allAccount = accountMapper.findAllAccount();
        for (AccountUser accountUser : allAccount){
            System.out.println(accountUser);
        }
    }

    //查询所有（一对一）
    @Test
    public void testFindAll(){
        List<Account> all = accountMapper.findAll();
        for (Account account : all){
            System.out.println("--------------每个account的信息---------------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

}

