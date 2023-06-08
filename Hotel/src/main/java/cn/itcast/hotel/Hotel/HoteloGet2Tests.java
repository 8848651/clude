package cn.itcast.hotel.Hotel;
import cn.itcast.hotel.Dao.HotelDoc;
import cn.itcast.hotel.service.HotelService;
import cn.itcast.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Completion;
import java.io.IOException;
import java.util.Map;

@Component
public class HoteloGet2Tests {

    @Autowired
    private RestHighLevelClient client;

    /*
    * request.source().query
    * request.source().sort
    * request.source().from
    * request.source().highlighter
    * request.source().suggest()
    * */
    //排序
    public void testSortAndPage(int page,int size) throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.matchAllQuery());
        request.source().sort("price", SortOrder.ASC);
        request.source().from((page - 1) * size).size(size);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        sorts(response);
    }

    private void sorts(SearchResponse response) {
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

    //高亮
   public void testHighlight(String sp) throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.matchQuery("all", sp));
        request.source().highlighter(new HighlightBuilder().field("name").requireFieldMatch(false));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
       Highlights(response);
    }

    private void Highlights(SearchResponse response) {
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        System.out.println("共搜索到" + total + "条数据");
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);

            Map<String, HighlightField> map = hit.getHighlightFields();
            HighlightField highlightField = map.get("name");
            String hName = highlightField.getFragments()[0].toString();
            hotelDoc.setName(hName);

            System.out.println("hotelDoc = " + hotelDoc);
        }
    }

    //自动补全容器
    public void testMatchAll() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().suggest(new SuggestBuilder().addSuggestion(
                "自动补全器名称",
                SuggestBuilders
                        .completionSuggestion("要补全的字段")
                        .prefix("关键字")
                        .skipDuplicates(true)//跳过重复
                        .size(10)
        ));
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
    }

    private void searchs(SearchResponse response) {
        Suggest suggest = response.getSuggest();
        CompletionSuggestion Suggestion=suggest.getSuggestion("自动补全器名称");
       for (CompletionSuggestion.Entry.Option option:Suggestion.getOptions()) {
           String s = option.getText().toString();
           System.out.println(s);
       }
    }
}
