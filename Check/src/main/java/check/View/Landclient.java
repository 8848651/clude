package check.View;
import check.Dao.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 86151
 */
@FeignClient(name="Landing",path = "/Landing")
public interface Landclient {
    @PostMapping("/insert")
    Boolean insert1(@RequestBody Message message);

    @PostMapping("/CS")
    Boolean insert2(@RequestBody Message message);
}
