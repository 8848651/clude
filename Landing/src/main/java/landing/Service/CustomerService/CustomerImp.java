package landing.Service.CustomerService;

import landing.Dao.Customer;

public interface CustomerImp {
    //增
    public Boolean LandAdd(Customer Customer);
    //改
    public Boolean LandChange(Customer Customer);
    //查
    public Boolean Landcheck(Customer Customer);
}
