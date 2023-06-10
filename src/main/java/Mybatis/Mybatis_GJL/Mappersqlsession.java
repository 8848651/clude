package Mybatis.Mybatis_GJL;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Mappersqlsession {
    public static SqlSession Sqlsessions() throws IOException {
        //1，加载Mybatis核心配置文件获取SqlSessionFactory对象
        String resource = "mybatis-config1.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2，返回 sqlsession对象
        return sqlSessionFactory.openSession();
    }
    public static SqlSession Sqlsessions1() throws IOException {
        String resource = "mybatis-config2.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }
}
