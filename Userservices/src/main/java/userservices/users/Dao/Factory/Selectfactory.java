package userservices.users.Dao.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import userservices.users.Dao.Daoint.Mappers;
import userservices.users.Dao.Daoint.Userservice;

import java.util.List;

@Repository
public class Selectfactory {

    @Autowired
    private Mappers mapper;

    public List CeSHi(String cno){
        List<Userservice> use=mapper.selectOne(cno);
       return use;
    }

}
