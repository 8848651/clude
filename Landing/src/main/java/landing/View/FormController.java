package landing.View;

import landing.Dao.Message;
import landing.Service.MesssageService.MessageImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Landing")
@ResponseBody
public class FormController {

    @Autowired
    private MessageImp MI;

    @RequestMapping("/save")
    public String Formsave1(){
        System.out.println("{'Spring':'MVC'}");
        return "ABCDEFG";
    }

    @PostMapping("/insert")
    public  Boolean insert1(@RequestBody Message Message) throws InterruptedException {
        return MI.insert(Message);
    }

    @PostMapping("/CS")
    public  Boolean insert2(@RequestBody Message Message) throws InterruptedException {
        System.out.println(Message.toString());
        return MI.insert(Message);
    }
}
