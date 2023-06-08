package oservices.order.View.Vieimp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import oservices.order.Dao.Factory.Selectfactory;
import oservices.order.Dao.Factory.Userservice;
import oservices.order.Dao.Factory.Usersorder;
import oservices.order.View.Orderclint.Orderclient;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/Order")
public class FormController {
    @Autowired
    private Selectfactory ss;
    @Autowired
    private Orderclient oc;

    @RequestMapping("/save")
    public String Formsave1(){
        System.out.println("{'Spring':'MVC'}");
        return "{'Spring':'Formsave1'}";
    }

    @RequestMapping("/save/{name}")
    public Usersorder Formsave2(@PathVariable String name){
        Usersorder usersorder = ss.CeSHi(name);
        return usersorder;
    }

    @GetMapping("/{name}")
    public Usersorder Selectone(@PathVariable String name) {
        Usersorder usersorder = ss.CeSHi(name);
        List<Userservice> users =oc.selectUserserviceList(name);
        usersorder.setUserservice(users);
        return usersorder;
    }
}
