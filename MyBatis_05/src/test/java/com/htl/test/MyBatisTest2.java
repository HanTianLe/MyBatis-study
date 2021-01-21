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

public class MyBatisTest2 {
    /**
     * 由于测试众多，优化代代码如下：
     */
    private InputStream resourceAsStream;       //输入流对象
    private SqlSessionFactory sqlSessionFactory;//SqlSessionFactory工厂对象
    //private SqlSession sqlSession;              //sqlSession对象
    //private UserMapper userMapper;              //代理对象
    //private AccountMapper accountMapper;        //代理对象

    @Before // 在测试方法执行之前执行
    public void init() throws IOException {
        //1、读取配置文件
        resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2、创建SqlSessionFactory工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、用工厂生产sqlSession对象
        //sqlSession = sqlSessionFactory.openSession(true);
        //4、使用sqlSession创建接口代理对象（mapper）
        //accountMapper = sqlSession.getMapper(AccountMapper.class);
        //userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After // 在测试方法执行之后执行
    public void destroy() throws IOException {
        //提交事务（如果openSession已设置自动提交则不需要）
        //sqlSession.commit();
        //释放资源
        //sqlSession.close();
        resourceAsStream.close();
    }

    //测试二级缓存
    //二级缓存指的是Mybatis中SqlSessionFactory对象的缓存。
    //由同一个SqlSessionFactory对象创建的SqlSession共享其缓存。
    @Test
    public void testSecondLevelCache(){
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        User user1 = mapper1.findById(41);
        System.out.println(user1);
        sqlSession1.close();//一级缓存消失

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.findById(41);
        System.out.println(user2);
        sqlSession1.close();

        System.out.println(user1==user2);
        /**
         * 之所以是 false，因为二级缓存中存放的内容是【数据】，而不是【对象】，
         * 例如：{"id":41,"username":"张三","address":"北京"}
         * 使用了二级缓存，并不意味着直接获取对象。而是先创建一个对象，再将二级缓存中的数据存入对象中。
         * 所以两次对象地址不同。
         */

        /*
        当我们在使用二级缓存时，
        所缓存的类一定要实现java.io.Serializable接口，
        这种就可以使用序列化方式来保存对象。
        */

    }

}

