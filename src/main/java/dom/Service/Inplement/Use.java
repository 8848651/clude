package dom.Service.Inplement;

import org.springframework.stereotype.Service;

public interface Use {

    int insert(DAO.Implement.User user);

    void delete(String name);

    int update(DAO.Implement.User user);

    DAO.Implement.User Select(String name);
}
