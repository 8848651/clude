package com.learning.xczx_plus_learning_api;

import com.contest.xczx_plus_content_model.content.Dao.CoursePublish;
import com.learning.xczx_plus_learning_service.feignclient.feignclient1;
import com.learning.xczx_plus_learning_service.feignclient.feignclient2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XczxPlusLearningApiApplicationTests {

    @Autowired
    com.learning.xczx_plus_learning_service.feignclient.feignclient1 feignclient1;

    @Autowired
    com.learning.xczx_plus_learning_service.feignclient.feignclient2 feignclient2;

    @Test
    void contextLoads() {
        CoursePublish coursepublish = feignclient1.getCoursepublish(39L);
        System.out.println(coursepublish);
    }

}
