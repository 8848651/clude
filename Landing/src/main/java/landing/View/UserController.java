package landing.View;

import landing.Dao.Customer;
import landing.Service.CustomerService.CustomerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/User")
@ResponseBody
public class UserController {

    @Autowired
    private CustomerImp MI;

    @RequestMapping("/save")
    public String Formsave1(){
        System.out.println("测试");
        return "测试";
    }

    @PostMapping("/insert")
    public Boolean insert1(@RequestBody Customer Customer) throws InterruptedException {
        return MI.LandAdd(Customer);
    }

    @RequestMapping("/check")
    public Boolean Formcheck(@RequestBody Customer Customer, HttpServletResponse response){
        boolean flag = MI.Landcheck(Customer);
        if (flag) {
            Cookie cookie = new Cookie("Username", Customer.getUser());
            cookie.setMaxAge(3600*24);
            response.addCookie(cookie);
        }
        return flag;
    }

    @RequestMapping("/change")
    public Boolean Formchange(@RequestBody Customer Customer){
        return MI.LandChange(Customer);
    }

}
