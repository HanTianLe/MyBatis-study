package com.htl.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test3 {
    public static void main(String[] args) {
        // 加载MyBatis配置文件。
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /** 通过学生id查询（一对多）*/
        // 获取实现接口的代理对象。
        /*StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
        System.out.println(studentRepository.findById(1L));
        sqlSession.close();*/

        /** 通过班级id查询（一对多）*/
        // 获取实现接口的代理对象。
        /*ClassesRepository classesRepository = sqlSession.getMapper(ClassesRepository.class);
        System.out.println(classesRepository.findById(1L));
        sqlSession.close();*/

        /** 通过顾客id查询（多对多）*/
        /*CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
        System.out.println(customerRepository.findById(2L));
        sqlSession.close();*/

        /** 通过商品id查询（多对多）*/
        /*GoodsRepository goodsRepository = sqlSession.getMapper(GoodsRepository.class);
        System.out.println(goodsRepository.findById(3L));
        sqlSession.close();*/

        /** 延迟加载*/
        /*StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
        Student student = studentRepository.findByIdLazy(1L);
        System.out.println(student.getClasses());
        sqlSession.close();*/
    }
}

