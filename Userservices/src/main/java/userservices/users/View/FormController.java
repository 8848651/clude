package userservices.users.View;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
@ResponseBody
public class FormController {

    @RequestMapping("/User")
    public void search1() {
        System.out.println("Userservices服务启动了");
    }

}
