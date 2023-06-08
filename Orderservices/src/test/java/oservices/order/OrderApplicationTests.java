package oservices.order;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import oservices.order.Dao.HotelDoc;
import oservices.order.ES.Estart;

import java.io.IOException;

@SpringBootTest
class OrderApplicationTests {

	@Autowired
	private Estart estart;

	@Test
	void contextLoads() throws IOException {
		HotelDoc hotelDoc = estart.testGetDocumentById("36934");
		System.out.println(hotelDoc.toString());
	}

}
