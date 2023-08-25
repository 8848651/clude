package com.learning.xczx_plus_learning_service.feignclient;

import com.base.xczx_plus_base.base.model.RestResponse;
import com.contest.xczx_plus_content_model.content.Dao.CoursePublish;

public class Fallback implements feignclient1,feignclient2 {
    @Override
    public CoursePublish getCoursepublish(Long courseId) {
        return null;
    }

    @Override
    public String getPlayUrlByMediaId(String mediaId) {
        return null;
    }
}
