package oservices.order.Dao.Factory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usersorder {
    private String KeChengHao;
    private String KeChengMing;
    private Integer KeChengShiJian;
    private Integer KeChengFen;
    private List<Userservice> userservice;
}
