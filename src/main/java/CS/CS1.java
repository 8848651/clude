package CS;

import dom.Config.Config;
import DAO.Factory.FactorMapper;
import DAO.Implement.User;
import DAO.Interface.Mapper;
import dom.Service.Inplement.Use;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class CS1 {
    @Test//mybatis-config.xml
    public void test1() throws IOException {
        User user=new User();
        user.setName("8847");
        user.setPassword("8847");
        SqlSession SS = FactorMapper.Sqlsessions();
        Mapper mapper = SS.getMapper(Mapper.class);
        mapper.insert(user);
        SS.commit();
        SS.close();
    }
    @Test//Spring—mybatis
    public void test2() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        Mapper bean = ctx.getBean(Mapper.class);
        User select = bean.Select("8848");

        System.out.println(select.toString());
    }
    @Test
    public void test3() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        Use bean = ctx.getBean(Use.class);
        User select = bean.Select("8848");

        System.out.println(select.toString());
    }
}
