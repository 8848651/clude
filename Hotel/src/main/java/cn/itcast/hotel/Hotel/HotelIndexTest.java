package cn.itcast.hotel.Hotel;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;


@Component
public class HotelIndexTest {

    @Autowired
    private RestHighLevelClient client;

   /* public void testCreateIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("hotel");
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        client.indices().create(request, RequestOptions.DEFAULT);
    }*/

    //查看是否存在
    public void testExistsIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("hotel");
        boolean isExists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(isExists ? "存在" : "不存在");
    }

    //删除
    public void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("hotel");
        client.indices().delete(request, RequestOptions.DEFAULT);
    }
}
