package landing.Service.RedisService;

import com.alibaba.fastjson.JSON;
import landing.Dao.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class Redis1 {

    @Autowired
    private Jedis Jedis;

    public void set(Customer Customer){
        Jedis.select(0);
        Jedis.set(Customer.getUser(),Customer.getPassword());
    }

    public String get(Customer Customer){
        Jedis.select(0);
        return Jedis.get(Customer.getUser());
    }

    public void delete(Customer Customer){
        Jedis.select(0);
        Jedis.del(Customer.getUser());
    }

}
