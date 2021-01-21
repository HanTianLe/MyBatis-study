package com.htl.test;

import com.htl.domain.User;
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
    private InputStream inputStream;            //输入流对象
    private SqlSessionFactory sqlSessionFactory;//SqlSessionFactory工厂对象
    private SqlSession sqlSession;              //sqlSession对象
    private UserMapper userMapper;              //代理对象

    @Before // 在测试方法执行之前执行
    public void init() throws IOException {
        //1、读取配置文件
        inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2、创建SqlSessionFactory工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //3、用工厂生产sqlSession对象
        sqlSession = sqlSessionFactory.openSession(true);
        //4、使用sqlSession创建接口代理对象（mapper）
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After // 在测试方法执行之后执行
    public void destroy() throws IOException {
        //提交事务（如果openSession已设置自动提交则不需要）
        //sqlSession.commit();
        //释放资源
        sqlSession.close();
        inputStream.close();
    }

    //查询所有用户，并查出其所有的账户（一对多）
    @Test
    public void testFindAll(){
        List<User> userList = userMapper.findAll();
        for (User user : userList){
            System.out.println("--------------------------每个用户的信息-----------------------------");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    //根据id查询用户
    @Test
    public void testFindById(){
        User user = userMapper.findById(1);
        System.out.println(user);

        //测试一级缓存
        //User user2 = userMapper.findById(1);
        //System.out.println(user2);
        //System.out.println(user==user2);

    }

}

