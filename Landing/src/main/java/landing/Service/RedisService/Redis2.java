package landing.Service.RedisService;

import landing.Dao.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class Redis2 {

    @Autowired
    private Jedis Jedis;

    public void set(Customer Customer){
        Jedis.select(1);
        Jedis.set(Customer.getUser(),Customer.getPassword());
    }

}
