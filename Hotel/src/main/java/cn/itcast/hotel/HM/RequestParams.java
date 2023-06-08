package cn.itcast.hotel.HM;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RequestParams {
    private String key;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String brand;
    private String city;
    private String starName;
    private Integer minPrice;
    private Integer maxPrice;
    private String location;
}
