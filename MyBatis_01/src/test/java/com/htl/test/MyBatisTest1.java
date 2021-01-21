package com.htl.test;

import com.htl.domain.QueryVo;
import com.htl.domain.User;
import com.htl.domain.User2;
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
import java.util.Date;
import java.util.List;

public class MyBatisTest1 {
//    @Test
//    public void testFindAll() throws IOException {
//        //1、读取配置文件
//        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        //2、创建SqlSessionFactory工厂
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        //3、用工厂生产sqlSession对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //4、使用sqlSession创建接口代理对象（mapper）
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        //5、使用代理对象执行方法
//        List<User> userList = mapper.findAll();
//        for (User user : userList){
//            System.out.println(user);
//        }
//        //6、释放资源
//        sqlSession.close();
//        resourceAsStream.close();
//    }
//    @Test
//    public void testSaveUser() throws IOException {
//        User user = new User();
//        user.setUsername("李四");
//        user.setBirthday(new Date());
//        user.setSex("男");
//        user.setAddress("淮安");
//
//        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        mapper.saveUser(user);
//        //提交事务
//        sqlSession.commit();
//        sqlSession.close();
//        resourceAsStream.close();
//    }

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
    public void testSaveUser(){
        User user = new User();
        user.setUsername("韩天琪");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("淮安");
        System.out.println("保存操作之前的id："+user.getId());
        mapper.saveUser(user);
        System.out.println("保存操作之后的id："+user.getId());
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(45);
        user.setUsername("韩天喜");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("淮安");
        mapper.updateUser(user);
    }

    @Test
    public void testDeleteUser(){
        mapper.deleteUser(51);
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
    public void testFindByName2(){
        List<User> userList = mapper.findByName2("韩");
        for (User user : userList){
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal(){
        int count = mapper.findTotal();
        System.out.println(count);
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

    //实体类属性与数据库列名不对应2种解决方式
    //方法1
    @Test
    public void testFindAll2(){
        List<User2> userList = mapper.findAll2();
        for (User2 user2 : userList){
            System.out.println(user2);
        }
    }
    //方法2
    @Test
    public void testFindAll3(){
        List<User2> userList = mapper.findAll3();
        for (User2 user2 : userList){
            System.out.println(user2);
        }
    }

}

