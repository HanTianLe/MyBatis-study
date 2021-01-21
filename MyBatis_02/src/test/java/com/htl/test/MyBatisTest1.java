package com.htl.test;

import com.htl.domain.QueryVo;
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
import java.util.ArrayList;
import java.util.List;

public class MyBatisTest1 {
    /**
     * 由于测试众多，优化代代码如下：
     */
    private InputStream resourceAsStream;       //输入流对象
    private SqlSessionFactory sqlSessionFactory;//SqlSessionFactory工厂对象
    private SqlSession sqlSession;              //sqlSession对象
    private UserMapper mapper;                  //代理对象

    @Before // 在测试方法执行之前执行
    public void init() throws IOException {
        //1、读取配置文件
        resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2、创建SqlSessionFactory工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、用工厂生产sqlSession对象
        sqlSession = sqlSessionFactory.openSession();
        //4、使用sqlSession创建接口代理对象（mapper）
        mapper = sqlSession.getMapper(UserMapper.class);
    }

    @After // 在测试方法执行之后执行
    public void destroy() throws IOException {
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        resourceAsStream.close();
    }

    @Test
    public void testFindAll(){
        List<User> userList = mapper.findAll();
        for (User user : userList){
            System.out.println(user);
        }
    }

    @Test
    public void testFindById(){
        User user = mapper.findById(1);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){
        List<User> userList = mapper.findByName("%韩%");
        for (User user : userList){
            System.out.println(user);
        }
    }

    @Test
    public void testFindUserByVo(){
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("%韩%");
        queryVo.setUser(user);
        List<User> userList = mapper.findUserByVo(queryVo);
        for (User user2 : userList){
            System.out.println(user2);
        }
    }

    // <if>标签、<where>标签
    @Test
    public void testFindUserByCondition(){
        User user = new User();
        user.setUsername("张三");
        user.setSex("女");
        List<User> userList = mapper.findUserByCondition(user);
        for (User u : userList){
            System.out.println(u);
        }
    }

    // <foreach>标签
    @Test
    public void testFindUserInIds(){
        QueryVo queryVo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(45);
        list.add(53);
        queryVo.setIds(list);
        List<User> userList = mapper.findUserInIds(queryVo);
        for (User user : userList){
            System.out.println(user);
        }
    }

}

