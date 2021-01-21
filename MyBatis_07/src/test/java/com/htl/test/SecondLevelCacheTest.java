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

public class SecondLevelCacheTest {
    private InputStream inputStream;            //输入流对象
    private SqlSessionFactory sqlSessionFactory;//SqlSessionFactory工厂对象
    private SqlSession sqlSession;              //sqlSession对象
    private UserMapper userMapper;        //代理对象

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
        //sqlSession.close();
        inputStream.close();
    }

    @Test
    public void testFindById(){
        User user = userMapper.findById(1);
        System.out.println(user);
        sqlSession.close();//释放一级缓存

        SqlSession sqlSession1 = sqlSessionFactory.openSession();//再次打开session
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        User user2 = userMapper1.findById(1);
        System.out.println(user2);
        System.out.println(user==user2);
        /**
         * 配置二级缓存：在sqlMapConfig.xml中添加二级缓存配置（其实不写也行，默认开启的）
         *          在要开启二级缓存的方法所在的mapper类上，添加注解：@CacheNamespace(blocking = true)
         */
    }
}

