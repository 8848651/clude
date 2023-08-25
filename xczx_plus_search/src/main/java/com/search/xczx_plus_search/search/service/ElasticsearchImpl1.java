package com.search.xczx_plus_search.search.service;
import com.alibaba.fastjson.JSON;
import com.search.xczx_plus_search.search.Dao.CourseIndex;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.GetIndexRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ElasticsearchImpl1 {

    @Autowired
    private RestHighLevelClient client;

    @Value("${elasticsearch.course.index}")
    private String DocumentIndex;

    //查看表结构是否存在
    public Boolean ExistsStructure() throws IOException {
        GetIndexRequest request = new GetIndexRequest(DocumentIndex);
        boolean isExists = client.indices().exists(request, RequestOptions.DEFAULT);
        return isExists;
    }

    //删除表结构
    public void DeleteStructure() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(DocumentIndex);
        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    //增加文档
    public Boolean AddDocument(CourseIndex courseIndex) throws IOException {
        String json=JSON.toJSONString(courseIndex);
        String ID= courseIndex.getId().toString();
        IndexRequest request = new IndexRequest(DocumentIndex).id(ID);
        request.source(json, XContentType.JSON);
        IndexResponse index = client.index(request, RequestOptions.DEFAULT);
        String name = index.getResult().name();
        return name.equalsIgnoreCase("created") || name.equalsIgnoreCase("updated");
    }

    //删除文档
    public Boolean DeleteDocument(String ID) throws IOException {
        DeleteRequest request = new DeleteRequest(DocumentIndex,ID);
        DeleteResponse delete = client.delete(request, RequestOptions.DEFAULT);
        DocWriteResponse.Result result = delete.getResult();
        return result.name().equalsIgnoreCase("deleted");
    }
    
    //修改文档
    public Boolean UpdateDocument(CourseIndex courseIndex) throws IOException {
        String ID = courseIndex.getId().toString();
        String jsonString = JSON.toJSONString(courseIndex);
        UpdateRequest updateRequest = new UpdateRequest(DocumentIndex,ID);
        updateRequest.doc(jsonString, XContentType.JSON);
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        DocWriteResponse.Result result = updateResponse.getResult();
        return result.name().equalsIgnoreCase("updated");

    }

    //查询文档，根据ID查
    public CourseIndex GetDocumentById(String ID) throws IOException {
        GetRequest request = new GetRequest(DocumentIndex, ID);
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        String json = response.getSourceAsString();
        CourseIndex courseIndex = JSON.parseObject(json, CourseIndex.class);
        return courseIndex;
    }

    //查询所有数据
    public List MatchAll() throws IOException {
        SearchRequest request = new SearchRequest(DocumentIndex);
        request.source().query(QueryBuilders.matchAllQuery());
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        List<CourseIndex> list = handleResponse(response);
        return list;
    }


    //Term精确查询根据 字段：field 内容：content 用于非分词
    public List TermSelect(String field,String content) throws IOException {
        SearchRequest request = new SearchRequest(DocumentIndex);
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(field, content);
        request.source().query(termQueryBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        List list = handleResponse(response);
        return list;
    }

    //Match模糊查询根据 字段：field 内容：content 用于分词
    public List MatchSelect(String field,String content) throws IOException {
        SearchRequest request = new SearchRequest(DocumentIndex);
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(field, content);
        request.source().query(matchQueryBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        List list = handleResponse(response);
        return list;
    }

    //多条件查询，must()查询条件，filter()过滤条件
    public void MultipleSelect(String city,Integer price) throws IOException {
        SearchRequest request = new SearchRequest(DocumentIndex);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        TermQueryBuilder Term = QueryBuilders.termQuery("city", city);
        boolQuery.must(Term);
        RangeQueryBuilder Range = QueryBuilders.rangeQuery("price").lte(price);
        boolQuery.filter(Range);
        request.source().query(boolQuery);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }

    //聚合
    public void Highlight() throws IOException {
        SearchRequest request = new SearchRequest(DocumentIndex);
        request.source().size(0);
        request.source().aggregation(AggregationBuilders
                .terms("myname")
                .field("name")
                .size(10)
        );
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //结果处理
        Aggregations aggregations=response.getAggregations();
        Terms brandTerms = aggregations.get("myname");
        List<? extends Terms.Bucket> buckets = brandTerms.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            String key = bucket.getKeyAsString();
        }
    }

    //高亮
    public void Highlight(String name1) throws IOException {
        SearchRequest request = new SearchRequest(DocumentIndex);
        request.source().query(QueryBuilders.matchQuery("name", name1));
        HighlightBuilder HighlightBuilder = new HighlightBuilder();
        HighlightBuilder.field("name").requireFieldMatch(false);

        request.source().highlighter(HighlightBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Highlights(response);
    }
    //高亮处理
    private void Highlights(SearchResponse response) {
        SearchHits searchHits = response.getHits();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            Map<String, HighlightField> map = hit.getHighlightFields();
            HighlightField highlightField = map.get("name");
            String hName = highlightField.getFragments()[0].toString();
        }
    }

    //解析查询数据
    private List handleResponse(SearchResponse response) {
        List<CourseIndex> list = new ArrayList<>();
        SearchHits searchHits = response.getHits();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            CourseIndex courseIndex = JSON.parseObject(json, CourseIndex.class);
            list.add(courseIndex);
        }
        return list;
    }



}
