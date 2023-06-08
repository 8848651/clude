package oservices.order.Dao.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import oservices.order.Dao.Daoint.Mappers;

@Repository
public class Selectfactory {

    @Autowired
    private Mappers mapper;

    public Usersorder CeSHi(String name){
        return mapper.selectOne(name);
    }

}
