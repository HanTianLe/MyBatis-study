package com.htl.test;

import com.htl.domain.User4;
import com.htl.mapper.AccountMapper;
import com.htl.mapper.RoleMapper;
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

public class MyBatisTest4 {
    /**
     * 由于测试众多，优化代代码如下：
     */
    private InputStream resourceAsStream;       //输入流对象
    private SqlSessionFactory sqlSessionFactory;//SqlSessionFactory工厂对象
    private SqlSession sqlSession;              //sqlSession对象
    private UserMapper userMapper;              //代理对象
    private AccountMapper accountMapper;        //代理对象
    private RoleMapper roleMapper;              //代理对象

    @Before // 在测试方法执行之前执行
    public void init() throws IOException {
        //1、读取配置文件
        resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2、创建SqlSessionFactory工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、用工厂生产sqlSession对象
        sqlSession = sqlSessionFactory.openSession();
        //4、使用sqlSession创建接口代理对象（mapper）
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After // 在测试方法执行之后执行
    public void destroy() throws IOException {
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        resourceAsStream.close();
    }

    //查询所有[人物对应角色]（多对多）
    //一个用户可以有多个角色，
    //一个角色可以赋予多个用户。
    @Test
    public void testFindAllUserRole(){
        List<User4> allUserRole = userMapper.findAllUserRole();
        for (User4 user4 : allUserRole){
            System.out.println("--------------每个人物角色的信息---------------");
            System.out.println(user4);
            System.out.println(user4.getRole4s());
        }
    }

}

