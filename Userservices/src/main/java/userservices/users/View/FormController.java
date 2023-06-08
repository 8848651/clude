package userservices.users.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import userservices.users.Dao.Factory.Selectfactory;

import java.util.List;

@RestController
@RequestMapping("/User")
@ResponseBody
public class FormController {

    @Autowired
    private Selectfactory ss;

    @RequestMapping("/save")
    public String Formsave1(){
        System.out.println("{'Spring':'MVC'}");
        return "{'Spring':'Formsave1'}";
    }

    @GetMapping("/{name}")
    public List Select(@PathVariable String name) {
        return ss.CeSHi(name);
    }
}
