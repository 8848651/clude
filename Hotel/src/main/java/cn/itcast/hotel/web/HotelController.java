package cn.itcast.hotel.web;

import cn.itcast.hotel.HM.PageResult;
import cn.itcast.hotel.HM.RequestParams;
import cn.itcast.hotel.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    //{key: "", page: 1, size: 5, sortBy: "default"}
    @RequestMapping("list")
    public PageResult search1(@RequestBody RequestParams params) throws IOException {
        System.out.println(params.toString());
        return hotelService.search(params);
    }

    @RequestMapping("list/{id}")
    public void search2(@PathVariable Long id) {
        System.out.println("id:"+id);
    }
}
