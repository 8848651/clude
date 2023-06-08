package oservices.order.Dao;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Hotel {
    private Long id;
    private String name;
    private String address;
    private Integer price;
    private Integer score;
    private String brand;
    private String city;
    private String starName;
    private String business;
    private String longitude;
    private String latitude;
    private String pic;
}
