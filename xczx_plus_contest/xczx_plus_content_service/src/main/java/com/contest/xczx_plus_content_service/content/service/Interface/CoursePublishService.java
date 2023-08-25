package com.contest.xczx_plus_content_service.content.service.Interface;


import com.contest.xczx_plus_content_model.content.Dao.CoursePublish;
import com.messdk.xczx_plus_messdk.po.MqMessage;

public interface CoursePublishService {

    //全部信息加入到审核表
    void CommitAudit(Long companyId, Long courseId);


    //添加到发布表
    void CommitPublish(Long companyId, Long courseId);


    //根据id查询是否发布
    CoursePublish SelectCoursePublish(Long id);

}
