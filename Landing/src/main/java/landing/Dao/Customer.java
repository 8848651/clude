package landing.Dao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Customer {
    private String User;
    private String Password;
}
