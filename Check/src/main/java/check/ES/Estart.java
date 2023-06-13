package check.ES;
import check.Dao.Message;
import check.Dao.PageResult;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.elasticsearch.client.indices.GetIndexRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class Estart {

    private final static String index = "message";

    @Autowired
    private RestHighLevelClient client;


    //映射表是否存在
    public void ExistsIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
        boolean isExists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(isExists ? "存在" : "不存在");
    }

    //判断文档ID是否存在
    public Boolean Docexit(String id) throws IOException {
        GetRequest getRequest = new GetRequest(index, "_doc", id);
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        return client.exists(getRequest, RequestOptions.DEFAULT);
    }

    //查
    public PageResult queryMatch(String sp) throws IOException {
        SearchRequest request = new SearchRequest(index);
        request.source().query( QueryBuilders.matchQuery("message", sp));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return handleResponse(response);
    }

    public PageResult termMatch(String sp) throws IOException {
        SearchRequest request = new SearchRequest(index);
        request.source().query( QueryBuilders.termQuery("name", sp));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return handleResponse(response);
    }

    //增
    public void testAdd(String json,String ID) throws IOException {
        IndexRequest request = new IndexRequest(index).id(ID);
        request.source(json, XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
    }

    //删
    public void testDeleteById(String id) throws IOException {
        DeleteRequest request = new DeleteRequest(index, id);
        client.delete(request, RequestOptions.DEFAULT);
    }

    //改
    public void testExchange(String json,String ID) throws IOException {
        testDeleteById(ID);
        testAdd(json,ID);
    }

    private PageResult handleResponse(SearchResponse response) {
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        System.out.println("共搜索到" + total + "条数据");
        SearchHit[] hits = searchHits.getHits();
        List<Message> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            Message Message = JSON.parseObject(json, Message.class);
            list.add(Message);
        }
        return new PageResult(total,list);
    }
}
