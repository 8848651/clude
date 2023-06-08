package amqp;
import amqp.pop.pop;
import amqp.push.push;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
@RunWith(SpringRunner.class)
@SpringBootTest
class AmqpApplicationTests {

	@Autowired
	private amqp.push.push push;

	@Test
	void contextLoads1() {
		push.SetQ();
	}
	@Test
	void contextLoads2() {
		push.SetE();
	}
	@Test
	void contextLoads3() {
		push.SetE01();
	}
	@Test
	void contextLoads4() {
		push.SetE02();
	}
	@Test
	void contextLoads5() throws JsonProcessingException {
		push.Setjson1();
	}
	@Test
	void contextLoads6() {
		push.Setjson2();
	}

}
