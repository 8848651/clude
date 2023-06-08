package userservices.users.View;

import com.alibaba.fastjson.JSON;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import userservices.users.Dao.Hotel;
import userservices.users.Dao.HotelDoc;
import userservices.users.service.HotelService;

import java.io.IOException;

@RestController
@RequestMapping("/User")
@ResponseBody
public class FormController {
    @Autowired
    private HotelService HI;


    @RequestMapping("/User1")
    public void search1() {
        System.out.println("Userservices服务启动了");
    }

    @RequestMapping("/{id}")
    public void search2(@PathVariable String id) throws IOException {
        Hotel hotel = HI.selectOne((long) Integer.parseInt(id));
        System.out.println(hotel);
    }

    @RequestMapping("/User2")
    public void search2() {
        String a="{\n" +
                "   \"id\": 209044524,\n" +
                "   \"name\": \"安徽工业大学\",\n" +
                "   \"address\": \"湖东路\",\n" +
                "   \"price\": 10000000,\n" +
                "   \"score\": 37,\n" +
                "   \"brand\": \"7天酒店\",\n" +
                "   \"city\": \"马鞍山\",\n" +
                "   \"starName\": \"二钻\",\n" +
                "   \"business\": \"安徽工业大学\",\n" +
                "   \"longitude\": 121.47522,\n" +
                "   \"latitude\": 31.251433,\n" +
                "   \"pic\": \"https://m.tuniucdn.com/fb2/t1/G1/M00/3E/40/Cii9EVkyLrKIXo1vAAHgrxo_pUcAALcKQLD688AAeDH564_w200_h200_c1_t0.jpg\"\n" +
                "}";
        Hotel hotel = JSON.parseObject(a, Hotel.class);
        HI.insert(hotel);
    }

}
