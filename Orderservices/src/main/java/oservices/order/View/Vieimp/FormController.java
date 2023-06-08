package oservices.order.View.Vieimp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import oservices.order.Dao.HotelDoc;
import oservices.order.ES.Estart;

import java.io.IOException;

@RestController
@ResponseBody
@RequestMapping("/Order")
public class FormController {

    @Autowired
    private Estart es;

    @RequestMapping("/Order")
    public void search1() {
        System.out.println("Orderservices服务启动了");
    }

    @RequestMapping("/{id}")
    public void search2(@PathVariable String id) throws IOException {
        HotelDoc hotelDoc = es.testGetDocumentById(id);
        System.out.println(hotelDoc);
    }
}
