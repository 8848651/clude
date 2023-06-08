package oservices.order.ES;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import oservices.order.Dao.HotelDoc;
import java.io.IOException;


@Component
public class Estart {

    @Autowired
    private RestHighLevelClient client;


    //增
    public void testAddDocument(String json,String ID) throws IOException {
        System.out.println("Estart");
        IndexRequest request = new IndexRequest("hotel").id(ID);
        request.source(json, XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
    }

    //删
    public void testDeleteDocumentById(String id) throws IOException {
        DeleteRequest request = new DeleteRequest("hotel", id);
        client.delete(request, RequestOptions.DEFAULT);
    }

    //改
    public void testExchangeDocumentById(String json,String ID) throws IOException {
        testDeleteDocumentById(ID);
        testAddDocument(json,ID);
    }

    //查
    public HotelDoc testGetDocumentById(String id) throws IOException {
        GetRequest request = new GetRequest("hotel", id);
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        String json = response.getSourceAsString();
        HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
        return hotelDoc;
    }

}
