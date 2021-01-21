package com.htl.test;

import com.htl.domain.Account;
import com.htl.mapper.AccountMapper;
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

public class MyBatisTest2 {
    /**
     * 由于测试众多，优化代代码如下：
     */
    private InputStream inputStream;            //输入流对象
    private SqlSessionFactory sqlSessionFactory;//SqlSessionFactory工厂对象
    private SqlSession sqlSession;              //sqlSession对象
    private AccountMapper accountMapper;        //代理对象

    @Before // 在测试方法执行之前执行
    public void init() throws IOException {
        //1、读取配置文件
        inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2、创建SqlSessionFactory工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //3、用工厂生产sqlSession对象
        sqlSession = sqlSessionFactory.openSession(true);
        //4、使用sqlSession创建接口代理对象（mapper）
        accountMapper = sqlSession.getMapper(AccountMapper.class);
    }

    @After // 在测试方法执行之后执行
    public void destroy() throws IOException {
        //提交事务（如果openSession已设置自动提交则不需要）
        //sqlSession.commit();
        //释放资源
        sqlSession.close();
        inputStream.close();
    }

    //查询所有账户，并获取每个账户所属的用户信息（一对一）
    @Test
    public void testFindAll(){
        List<Account> accountList = accountMapper.findAll();
        for (Account account : accountList){
            System.out.println("--------------------------每个账户的信息-----------------------------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    //根据id查询账户
    @Test
    public void testFindAccountByUid(){
        List<Account> accountList = accountMapper.findAccountByUid(41);
        for (Account account : accountList){
            System.out.println(account);
        }
    }

}

