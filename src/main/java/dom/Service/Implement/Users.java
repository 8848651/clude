package dom.Service.Implement;
import DAO.Implement.User;
import DAO.Interface.Mapper;
import dom.Service.Inplement.Use;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Users implements Use {

    @Autowired
    private Mapper mapper;

    @Override
    public int insert(User user) {
        return mapper.insert(user);
    }

    @Override
    public void delete(String name) {
        mapper.delete(name);
    }

    @Override
    public int update(User user) {
        return mapper.update(user);
    }

    @Override
    public User Select(String name) {
        return mapper.Select(name);
    }
}
