package Affairs.Implement;

import DAO.Implement.User;
import DAO.Interface.Mapper;
import Affairs.Interface.Change;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class changes implements Change {

    @Autowired
    private Mapper mapper;
    @Override
    public void changed(User user1, User user2) {
        mapper.update(user1);
        //int a=1/0;
        mapper.update(user2);
    }
}
