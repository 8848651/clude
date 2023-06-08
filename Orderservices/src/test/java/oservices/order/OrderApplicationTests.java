package oservices.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import oservices.order.Dao.Daoint.Mappers;
import oservices.order.Dao.Factory.Usersorder;

@SpringBootTest
class OrderApplicationTests {

	@Autowired
	private Mappers mapper;

	@Test
	void contextLoads() {
		Usersorder usersorder = mapper.selectOne("001");
		System.out.println(usersorder);
	}

}
