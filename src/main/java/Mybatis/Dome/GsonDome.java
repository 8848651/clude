package Mybatis.Dome;

import Mybatis.Mybatis_GJL.Brand;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import org.junit.Test;

public class GsonDome {
    @Test
    public void testGsonDome() {
        Brand brand = new Brand(3,"小米", "小米有限公司", 50, "小米为发烧而生", 1);

        //Gson
        Gson gson = new Gson();
        String gson1 = gson.toJson(brand);
        System.out.println(gson1);
        //利用Gson实现深度克隆(将Josn转为对象)
        Brand brand1 = gson.fromJson(gson1, Brand.class);

        //Josn
        String json = JSON.toJSONString(brand);
        System.out.println(json);
        //将Josn转为对象
        Brand brand2 = JSON.parseObject(json, Brand.class);
        System.out.println(brand2);

    }
}
