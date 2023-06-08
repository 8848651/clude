package userservices.users;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import userservices.users.Dao.Hotel;
import userservices.users.Dao.HotelDoc;
import userservices.users.Dao.HotelMapper;


import java.util.List;

@SpringBootTest
class UsersApplicationTests {

	@Autowired
	private HotelMapper HM;

	@Test
	void test001(){
		String a="{\n" +
				"   \"id\": 36934,\n" +
				"   \"name\": \"7天连锁酒店(上海宝山路地铁站店)\",\n" +
				"   \"address\": \"静安交通路40号\",\n" +
				"   \"price\": 336,\n" +
				"   \"score\": 37,\n" +
				"   \"brand\": \"7天酒店\",\n" +
				"   \"city\": \"上海\",\n" +
				"   \"starName\": \"二钻\",\n" +
				"   \"business\": \"四川北路商业区\",\n" +
				"   \"longitude\": 121.47522,\n" +
				"   \"latitude\": 31.251433,\n" +
				"   \"pic\": \"https://m.tuniucdn.com/fb2/t1/G1/M00/3E/40/Cii9EVkyLrKIXo1vAAHgrxo_pUcAALcKQLD688AAeDH564_w200_h200_c1_t0.jpg\"\n" +
				"}";
		HotelDoc hotelDoc = JSON.parseObject(a, HotelDoc.class);
		System.out.println(hotelDoc);
	}


}
