package userservices.users;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import userservices.users.Dao.Daoint.Mappers;
import userservices.users.Dao.Daoint.Userservice;

import java.util.List;

@SpringBootTest
class UsersApplicationTests {

	@Autowired
	private Mappers mapper;

	@Test
	void contextLoads() {
		List<Userservice> userservices = mapper.selectOne("001");
		System.out.println(userservices);
	}

}
