package userservices.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import userservices.users.Dao.Hotel;
import userservices.users.Dao.HotelMapper;


import java.util.List;

@SpringBootTest
class UsersApplicationTests {

	@Autowired
	private HotelMapper HM;

	@Test
	void test001(){
		Hotel hotel = HM.selectById((long) 36934);
		System.out.println(hotel.toString());
	}


}
