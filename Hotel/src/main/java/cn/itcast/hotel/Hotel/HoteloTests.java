package cn.itcast.hotel.Hotel;
import cn.itcast.hotel.Dao.Hotel;
import cn.itcast.hotel.Dao.HotelDoc;
import cn.itcast.hotel.service.HotelService;
import cn.itcast.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class HoteloTests {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private HotelService HL;


    public void testAddDocument() throws IOException {
        HotelDoc hotelDoc = new HotelDoc(HL.selectOne((long) 36934));
        String json = JSON.toJSONString(hotelDoc);
        IndexRequest request = new IndexRequest("hotel").id(hotelDoc.getId().toString());
        request.source(json, XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
    }


    public HotelDoc testGetDocumentById(String id) throws IOException {
        GetRequest request = new GetRequest("hotel", id);
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        String json = response.getSourceAsString();
        HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
       return hotelDoc;
    }


    public void testDeleteDocumentById(String id) throws IOException {
        DeleteRequest request = new DeleteRequest("hotel", id);
        client.delete(request, RequestOptions.DEFAULT);
    }


    public void testUpdateById(String id,String pricecontent) throws IOException {
        UpdateRequest request = new UpdateRequest("hotel", id);
        request.doc("price", pricecontent);
        client.update(request, RequestOptions.DEFAULT);
    }


    //批量将数据库导入
    public void testBulkRequest() throws IOException {
        List<Hotel> list = hotelService.selectAll();
        BulkRequest request = new BulkRequest();
        for (Hotel hotel : list) {
            String json = JSON.toJSONString(new HotelDoc(hotel));
            request.add(new IndexRequest("hotel").id(hotel.getId().toString()).source(json, XContentType.JSON));
        }
        client.bulk(request, RequestOptions.DEFAULT);
    }
}
