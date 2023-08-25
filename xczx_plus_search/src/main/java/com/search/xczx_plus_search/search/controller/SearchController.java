package com.search.xczx_plus_search.search.controller;
import com.alibaba.fastjson.JSON;
import com.base.xczx_plus_base.base.model.PageParams;
import com.search.xczx_plus_search.search.Dao.CourseIndex;
import com.search.xczx_plus_search.search.Dao.SearchPageReturn;
import com.search.xczx_plus_search.search.Dao.SearchQuery;
import com.search.xczx_plus_search.search.service.ElasticsearchImpl1;
import com.search.xczx_plus_search.search.service.ElasticsearchImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class SearchController {

    @Autowired
    private ElasticsearchImpl1 ElasticsearchImpl1;
    @Autowired
    private ElasticsearchImpl2 ElasticsearchImpl2;


    //添加课程信息
    @PostMapping("/index/course")
    public Boolean add(@RequestBody CourseIndex courseIndex) throws IOException {
        System.out.println(courseIndex.getCreateDate());
        String string = JSON.toJSONString(courseIndex);
        System.out.println(string);
        Boolean aBoolean = ElasticsearchImpl1.AddDocument(courseIndex);
        return aBoolean;
    }

    //搜索课程信息
    @GetMapping("/course/list")
    public SearchPageReturn<CourseIndex> list(PageParams pageParams,SearchQuery SearchQuery){
        System.out.println(pageParams);;
        SearchPageReturn<CourseIndex> index = ElasticsearchImpl2.CoursePubIndex(pageParams, SearchQuery);
        return index;
    }
}
