package cn.itcast.hotel.HM;
import cn.itcast.hotel.Dao.HotelDoc;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import org.apache.commons.codec.language.bm.Rule;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HM_LY {

    @Autowired
    private RestHighLevelClient client;

    public PageResult QueryPagination(RequestParams RP) throws IOException {
        String key = RP.getKey();
        SearchRequest request = new SearchRequest("hotel");
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if(!key.isEmpty()){
            boolQuery.must( QueryBuilders.matchQuery("all", key));
        }else {
            boolQuery.must( QueryBuilders.matchAllQuery());
        }
        if (RP.getBrand()!=null&&!RP.getBrand().equals("")){
            boolQuery.filter(QueryBuilders.termQuery("brand",RP.getBrand()));
        }
        request.source().query(boolQuery);
        request.source().from((RP.getPage() - 1) * RP.getSize()).size(RP.getSize());
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return handleResponse(response);
    }


    private PageResult handleResponse(SearchResponse response) {
        PageResult PRESULT=new PageResult();
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        List<HotelDoc> HL=new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
            HL.add(hotelDoc);
        }
        PRESULT.setTotal(total);
        PRESULT.setHotels(HL);
        return  PRESULT;
    }

}
