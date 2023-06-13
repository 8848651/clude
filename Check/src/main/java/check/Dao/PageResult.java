package check.Dao;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class PageResult {
    private Long total;
    private List<Message> Message;

    public PageResult() {
    }

    public PageResult(Long total, List<Message> Message) {
        this.total = total;
        this.Message = Message;
    }
}
