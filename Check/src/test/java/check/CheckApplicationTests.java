package check;

import check.Dao.PageResult;
import check.ES.Estart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class CheckApplicationTests {

    @Autowired
    private Estart es;

    @Test
    void contextLoads1() throws IOException {
        PageResult result = es.queryMatch("太好");
        System.out.println(result.toString());
    }

    @Test
    void contextLoads2() throws IOException {
        PageResult result = es.termMatch("张三");
        System.out.println(result.toString());
    }

}
