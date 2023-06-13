package landing;

import landing.Dao.Message;
import landing.Dao.MessageMappers;
import landing.Service.MesssageService.MessageImt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LandApplicationTests {

    @Autowired
    private MessageMappers CM;

    @Autowired
    private MessageImt CI;

    @Test
    public void test1() throws InterruptedException {
        Message message = new Message("王五","git@github.com:8848651");
        CI.insert(message);
    }

    @Test
    public void test2() throws InterruptedException {
        List<Message> messages = CM.selectOne2("王五wy");
        System.out.println(messages );
    }

    @Test
    public void test3() throws InterruptedException {
        Message Message=new Message("26","王五","git@github.com:8848651");
        int update = CM.Update1(Message);
        System.out.println(update);
    }

    @Test
    public void test4() throws InterruptedException {
        Message Message=new Message("28","王五","100");
        int update = CM.Update2(Message);
        System.out.println(update);
    }


}
