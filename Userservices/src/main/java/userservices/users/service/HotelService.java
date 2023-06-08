package userservices.users.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userservices.users.Dao.Hotel;
import userservices.users.Dao.HotelMapper;
import userservices.users.MQ.push;

import java.util.List;

@Service
public class HotelService implements IHotelService {

    @Autowired
    private HotelMapper HM;

    @Autowired
    private push PP;


    @Override
    public Hotel selectOne(Long id) {
        return HM.selectById(id);
    }

    @Override
    public List<Hotel> selectAll() {
        return HM.selectList(null);
    }

    @Override
    public void insert(Hotel hh) {
        PP.Add(JSON.toJSONString(hh));
        HM.insert(hh);
    }

}
