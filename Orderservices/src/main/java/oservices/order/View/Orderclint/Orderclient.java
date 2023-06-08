package oservices.order.View.Orderclint;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient("gateway")
public interface Orderclient {
    //@GetMapping("/User/{name}")
    //List<Userservice> selectUserserviceList(@PathVariable("name") String cno);
}
