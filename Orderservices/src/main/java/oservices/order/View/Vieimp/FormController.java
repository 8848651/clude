package oservices.order.View.Vieimp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/Order")
public class FormController {

    @RequestMapping("/Order")
    public void search1() {
        System.out.println("Orderservices服务启动了");
    }

    @RequestMapping("/Order2")
    public void search2() {

    }
}
