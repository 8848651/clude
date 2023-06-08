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
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
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
public class HoteloGetTests {

    @Autowired
    private RestHighLevelClient client;

    public void testMatchAll() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.matchAllQuery());
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }


    public void testMatch(String sp) throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query( QueryBuilders.termQuery("brand", "君悦"));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }


    public void testBool(String city,Integer price) throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        // 2.2.添加term
        boolQuery.must(QueryBuilders.termQuery("city", city));
        // 2.3.添加range
        boolQuery.filter(QueryBuilders.rangeQuery("price").lte(price));
        request.source().query(boolQuery);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }

    private void handleResponse(SearchResponse response) {
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        System.out.println("共搜索到" + total + "条数据");
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
            System.out.println("hotelDoc = " + hotelDoc);
        }
    }

}
