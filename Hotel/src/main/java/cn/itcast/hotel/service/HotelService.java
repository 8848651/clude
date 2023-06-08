package cn.itcast.hotel.service;
import cn.itcast.hotel.Dao.Hotel;
import cn.itcast.hotel.Dao.HotelMapper;
import cn.itcast.hotel.HM.HM_LY;
import cn.itcast.hotel.HM.PageResult;
import cn.itcast.hotel.HM.RequestParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HotelService implements IHotelService {

    @Autowired
    private HotelMapper HM;

    @Autowired
    private HM_LY HL;

    @Override
    public Hotel selectOne(Long id) {
        return HM.selectById(id);
    }

    @Override
    public List<Hotel> selectAll() {
        return HM.selectList(null);
    }

    @Override
    public PageResult search(RequestParams params) throws IOException {
        return HL.QueryPagination(params);
    }
}
