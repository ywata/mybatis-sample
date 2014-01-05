package org.mybatis.example;

import java.io.IOException;
import java.io.InputStream;
 
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.example.User;
 
public class App {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SqlSession session = null;
        try {
            String resource = "mybatisConfig.xml";
            InputStream inputStream;
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            session = sqlSessionFactory.openSession();
            System.out.println(session.selectList("User.select"));

            User user = new User();
            user.setUserid(2);
            user.setEmail("a@b");
            user.setPassword("password");
            user.setTel("03-1234-5678");
            user.setUsername("My name");
            session.insert("insertUser", user);
            session.commit();

            System.out.println(session.selectList("User.select"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
}
