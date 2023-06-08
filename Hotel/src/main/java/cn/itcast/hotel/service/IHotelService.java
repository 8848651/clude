package cn.itcast.hotel.service;



import cn.itcast.hotel.Dao.Hotel;
import cn.itcast.hotel.HM.PageResult;
import cn.itcast.hotel.HM.RequestParams;

import java.io.IOException;
import java.util.List;


public interface IHotelService {
    public Hotel selectOne(Long id);
    public List<Hotel> selectAll();
    PageResult search(RequestParams params) throws IOException;
}
