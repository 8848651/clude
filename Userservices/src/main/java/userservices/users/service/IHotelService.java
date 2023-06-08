package userservices.users.service;


import userservices.users.Dao.Hotel;

import java.io.IOException;
import java.util.List;


public interface IHotelService {
    public Hotel selectOne(Long id);
    public List<Hotel> selectAll();
}
