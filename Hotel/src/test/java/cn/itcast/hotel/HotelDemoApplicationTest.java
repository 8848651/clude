package cn.itcast.hotel;

import cn.itcast.hotel.Dao.Hotel;
import cn.itcast.hotel.Dao.HotelDoc;
import cn.itcast.hotel.Dao.HotelMapper;
import cn.itcast.hotel.Hotel.HoteloGet2Tests;
import cn.itcast.hotel.Hotel.HoteloGetTests;
import cn.itcast.hotel.Hotel.HoteloTests;
import cn.itcast.hotel.service.HotelService;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;


@SpringBootTest
public class HotelDemoApplicationTest {

    @Autowired
    private HotelService HL;

    @Autowired
    private HoteloTests HT;

    @Autowired
    private HoteloGetTests HG;

    @Autowired
    private HoteloGet2Tests HG2;

    @Autowired
    private HotelMapper HM;

    @Test
    void test001(){
        Hotel hotel = HM.selectById((long) 36934);
        System.out.println(hotel.toString());
    }

    @Test
    void test002() throws IOException {
        HotelDoc hotelDoc = HT.testGetDocumentById("36934");
        System.out.println(hotelDoc.toString());
    }

    @Test
    void test003() throws IOException {
        HG2.testSortAndPage(1,10);
    }
}
