package com.search.xczx_plus_search.search.service;

import com.alibaba.fastjson.JSON;
import com.base.xczx_plus_base.base.model.PageParams;
import com.search.xczx_plus_search.search.Dao.CourseIndex;
import com.search.xczx_plus_search.search.Dao.SearchPageReturn;
import com.search.xczx_plus_search.search.Dao.SearchQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ElasticsearchImpl2 {
    @Autowired
    RestHighLevelClient client;

    @Value("${elasticsearch.course.index}")
    private String DocumentIndex;
    @Value("${elasticsearch.course.source_fields}")
    private String DocumentFields;

    public SearchPageReturn<CourseIndex> CoursePubIndex(PageParams pageParams, SearchQuery SearchQuery) {
        //设置索引
        SearchRequest searchRequest = new SearchRequest(DocumentIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        String[] sourceFieldsArray = DocumentFields.split(",");
        //只想在sourceFieldsArray字段中检索
        searchSourceBuilder.fetchSource(sourceFieldsArray, new String[]{});
        //设置查询条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(StringUtils.isNotEmpty(SearchQuery.getKeywords())){
            //多字段联合匹配
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(SearchQuery.getKeywords(), "name", "description");
            //要求占搜索字段的70%
            multiMatchQueryBuilder.minimumShouldMatch("70%");
            multiMatchQueryBuilder.field("name",10);
            boolQueryBuilder.must(multiMatchQueryBuilder);
        }
        if(StringUtils.isNotEmpty(SearchQuery.getMt())){
            boolQueryBuilder.filter(QueryBuilders.termQuery("mtName",SearchQuery.getMt()));
        }
        if(StringUtils.isNotEmpty(SearchQuery.getSt())){
            boolQueryBuilder.filter(QueryBuilders.termQuery("stName",SearchQuery.getSt()));
        }
        if(StringUtils.isNotEmpty(SearchQuery.getGrade())){
            boolQueryBuilder.filter(QueryBuilders.termQuery("grade",SearchQuery.getGrade()));
        }
        //分页
        Long pageNo = pageParams.getPageNo();
        Long pageSize = pageParams.getPageSize();
        int start = (int) ((pageNo-1)*pageSize);
        searchSourceBuilder.from(start);
        searchSourceBuilder.size(Math.toIntExact(pageSize));
        searchSourceBuilder.query(boolQueryBuilder);
        //高亮设置
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font class='eslight'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.fields().add(new HighlightBuilder.Field("name"));

        searchSourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(searchSourceBuilder);
        //聚合设置
        buildAggregation(searchRequest);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            return new SearchPageReturn<CourseIndex>(new ArrayList(),0,0,0);
        }

        //结果集处理
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        TotalHits totalHits = hits.getTotalHits();
        List<CourseIndex> list = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            String sourceAsString = hit.getSourceAsString();
            CourseIndex courseIndex = JSON.parseObject(sourceAsString, CourseIndex.class);
            String name =null;
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if(highlightFields!=null){
                HighlightField nameField = highlightFields.get("name");
                if(nameField!=null){
                    Text[] fragments = nameField.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text str : fragments) {
                        stringBuffer.append(str.string());
                    }
                    name = stringBuffer.toString();
                }
            }
            courseIndex.setName(name);
            list.add(courseIndex);
        }

        SearchPageReturn<CourseIndex> pageResult = new SearchPageReturn<>(list, totalHits.value,pageNo,pageSize);
        List<String> mtList= getAggregation(searchResponse.getAggregations(), "mtAgg");
        List<String> stList = getAggregation(searchResponse.getAggregations(), "stAgg");

        pageResult.setMtList(mtList);
        pageResult.setStList(stList);

        return pageResult;
    }


    //创建聚合
    private void buildAggregation(SearchRequest request) {
        request.source().aggregation(AggregationBuilders
                .terms("mtAgg")
                .field("mtName")
                .size(100)
        );
        request.source().aggregation(AggregationBuilders
                .terms("stAgg")
                .field("stName")
                .size(100)
        );
    }

    //取出聚合list
    private List<String> getAggregation(Aggregations aggregations, String aggName) {
        Terms brandTerms = aggregations.get(aggName);
        List<? extends Terms.Bucket> buckets = brandTerms.getBuckets();
        List<String> brandList = new ArrayList<>();
        for (Terms.Bucket bucket : buckets) {
            String key = bucket.getKeyAsString();
            brandList.add(key);
        }
        return brandList;
    }
}
