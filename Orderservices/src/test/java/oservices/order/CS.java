package oservices.order;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import oservices.order.Dao.HotelDoc;
import oservices.order.ES.Estart;

import java.io.IOException;

@SpringBootTest
public class CS {

    @Autowired
    private Estart es;

    @Test
    public void test01() throws IOException {
        HotelDoc hotelDoc = es.testGetDocumentById("36934");
        System.out.println(hotelDoc.toString());
    }
}
