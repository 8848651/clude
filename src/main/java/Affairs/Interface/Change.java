package Affairs.Interface;

import DAO.Implement.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
//事务的传播行为，如果一个事务
// @Transactional(propagation = Propagation.REQUIRES_NEW)
//代表在事务管理员下该事务不会回滚
public interface Change {
    void changed(User user1,User user2);
}
