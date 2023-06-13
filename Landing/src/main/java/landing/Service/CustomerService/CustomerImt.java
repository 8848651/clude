package landing.Service.CustomerService;
import landing.Dao.Customer;
import landing.Dao.CustomerMappers;
import landing.Service.RedisService.Redis1;
import landing.Service.RedisService.Redis2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerImt implements CustomerImp {

    @Autowired
    private CustomerMappers CustomerMappers;

    @Autowired
    private Redis1 Redis1;

    @Autowired
    private Redis2 Redis2;


    //增
    @Override
    public Boolean LandAdd(Customer Customer){
        return CustomerMappers.insert(Customer) > 0;
    }

    //改
    @Override
    public Boolean LandChange(Customer Customer){
        Redis1.delete(Customer);
        return CustomerMappers.updateById(Customer) > 0;
    }
    //查
    @Override
    public Boolean Landcheck(Customer Customer) {
        String password= Redis1.get(Customer);
        if (password == null) {
            Customer customer = CustomerMappers.selectOne(Customer.getUser());
            Redis1.set(customer);
            Redis2.set(customer);
            return Customer.equals(customer);
        }
        return Customer.getPassword().equals(password);
    }

}
