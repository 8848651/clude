package userservices.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userservices.users.Dao.Hotel;
import userservices.users.Dao.HotelMapper;

import java.util.List;

@Service
public class HotelService implements IHotelService {

    @Autowired
    private HotelMapper HM;


    @Override
    public Hotel selectOne(Long id) {
        return HM.selectById(id);
    }

    @Override
    public List<Hotel> selectAll() {
        return HM.selectList(null);
    }

}
