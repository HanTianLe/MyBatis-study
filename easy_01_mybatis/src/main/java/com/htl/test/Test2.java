package com.htl.test;

import com.htl.repository.AccountRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 *      Mapper代理自定义接口。——————测试
 *
 *      注意：
 *          insert、delete、update都需要事务提交：sqlSession.commit();
 *          而 select 不需要！
 *
 */
public class Test2 {
    public static void main(String[] args) {
        // 加载MyBatis配置文件。
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取实现接口的代理对象。
        AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
        /** 添加对象。*/
        /*Account account = new Account(2L,"韩世忠","123",15);
        accountRepository.save(account);
        // 提交事务
        sqlSession.commit();
        // 关闭sqlSession
        sqlSession.close();*/

        /** 通过id删除对象。*/
        /*int result = accountRepository.deleteById(8L);
        // 显示有几条记录被删除。
        System.out.println(result);
        sqlSession.commit();
        sqlSession.close();*/

        /** Java Bean，修改对象。*/
        /*Account account = accountRepository.findById(2L);
        account.setUsername("韩天喜");
        account.setPassword("456");
        account.setAge(23);
        int result = accountRepository.update(account);
        sqlSession.commit();
        // 显示有几条记录被修改了。
        System.out.println(result);
        sqlSession.close();*/

        /** 查询全部对象。*/
        /*List<Account> list = accountRepository.findAll();
        for (Account account : list){
            System.out.println(account);
        }
        sqlSession.close();*/

        /** Java Bean，通过id查询对象。*/
        /*Account account = accountRepository.findById(3L);
        System.out.println(account);
        sqlSession.close();*/

        /** String 类型，通过 name查询Account*/
        /*System.out.println(accountRepository.findByName("韩天乐"));
        sqlSession.close();*/

        /** 包装类，通过 Id查询Account*/
        /*Long id = Long.parseLong("1");     // 将String解析为Long。1后面不用加L，就会自动转成Long类型。
        System.out.println(accountRepository.findById2(id));
        sqlSession.close();*/

        /** 多个参数，通过name和age查询Account*/
        /*System.out.println(accountRepository.findByNameAndAge("韩天乐",23));
        sqlSession.close();*/

        /** 基本数据类型，统计 Account 总数*/
        /*System.out.println(accountRepository.count());
        sqlSession.close();*/

        /** 包装类，统计 Account 总数*/
        /*System.out.println(accountRepository.count2());
        sqlSession.close();*/

        /** String 类型，通过 id 查询 Account 的 name */
        System.out.println(accountRepository.findNameById(1L));
        sqlSession.close();
    }
}

