package CS;


import dom.Config.Config;
import DAO.Implement.User;
import DAO.Interface.Mapper;
import Affairs.Interface.Change;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class CS2 {
    @Autowired
    private Change change;
    @Autowired
    private Mapper mapper;

    @Test
    public void test(){
        User user1 = mapper.Select("8847");
        user1.setPassword("8847");
        User user2 = mapper.Select("8849");
        user2.setPassword("8847");

        change.changed(user1,user2);
    }

    @Test
    public void test2(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        Change bean = ctx.getBean(Change.class);
        System.out.println(bean.getClass());


        User user1 = mapper.Select("8847");
        user1.setPassword("8847");
        User user2 = mapper.Select("8849");
        user2.setPassword("8847");

        bean.changed(user1,user2);

    }
}
