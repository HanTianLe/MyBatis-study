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

public class MyBatisTest1 {
    /**
     * 由于测试众多，优化代代码如下：
     */
    private InputStream resourceAsStream;       //输入流对象
    private SqlSessionFactory sqlSessionFactory;//SqlSessionFactory工厂对象
    private SqlSession sqlSession;              //sqlSession对象
    private UserMapper userMapper;              //代理对象
    //private AccountMapper accountMapper;        //代理对象

    @Before // 在测试方法执行之前执行
    public void init() throws IOException {
        //1、读取配置文件
        resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2、创建SqlSessionFactory工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、用工厂生产sqlSession对象
        sqlSession = sqlSessionFactory.openSession(true);
        //4、使用sqlSession创建接口代理对象（mapper）
        //accountMapper = sqlSession.getMapper(AccountMapper.class);
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

    //测试一级缓存
    @Test
    public void testFirstLevelCache(){
        User user = userMapper.findById(53);
        System.out.println(user);

        /** 因为一级缓存是指SqlSession对象的缓存，当我们执行查询之后，
         * 查询的结果会同时存入到 SqlSession为我们提供的一块区域中。该区域的结构是一个Map。
         * 当我们再次查询同样的数据，mybatis会先去sqlSession中查询是否有，有的话直接拿出来用。
         * 所以当关闭sqlSession，再重新启动时，两次得到 user对象地址就不一样了。*/
        //sqlSession.close();
        //sqlSession = sqlSessionFactory.openSession();
        //userMapper = sqlSession.getMapper(UserMapper.class);

        sqlSession.clearCache();//此方法也可以清空缓存

        User user1 = userMapper.findById(53);
        System.out.println(user1);
        //对比地址
        System.out.println(user == user1);
    }

    //测试一级缓存
    @Test
    public void testClearCache(){
        //查询
        User user = userMapper.findById(41);
        System.out.println(user);

        //更新
        user.setUsername("张三update");
        user.setAddress("南京update");
        /** 一级缓存是SqlSession范围的缓存，当调用SqlSession的修改，添加，删除，commit()，close()等方法时，
         *  就会清空一级缓存。*/
//        userMapper.updateUser(user);    //注释前后，对比地址结果是不一样的。

        //再次查询id=41的
        User user1 = userMapper.findById(41);
        System.out.println(user1);
        //对比地址
        System.out.println(user == user1);
    }


}

