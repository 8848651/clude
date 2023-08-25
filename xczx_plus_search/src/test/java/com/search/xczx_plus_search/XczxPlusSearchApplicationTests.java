package com.search.xczx_plus_search;

import com.search.xczx_plus_search.search.Dao.CourseIndex;
import com.search.xczx_plus_search.search.service.ElasticsearchImpl1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class XczxPlusSearchApplicationTests {

    @Autowired
    ElasticsearchImpl1 Elasticsearch;

    @Test
    void contextLoads() throws IOException {
        CourseIndex courseIndex = Elasticsearch.GetDocumentById("1");
        System.out.println(courseIndex);
    }

}
