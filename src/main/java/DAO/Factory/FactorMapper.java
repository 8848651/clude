package DAO.Factory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
@Repository
public class FactorMapper {
    private static SqlSession sqlSession;
    static{
        InputStream inputStream = null;
        String resource=null;
        SqlSessionFactory sqlSessionFactory=null;
        try {
            resource = "mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Bean("SqlSession")
    public static SqlSession Sqlsessions() throws IOException {
        return sqlSession;
    }
}
