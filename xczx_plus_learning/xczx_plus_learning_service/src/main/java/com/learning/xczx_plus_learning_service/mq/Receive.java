package com.learning.xczx_plus_learning_service.mq;


import com.learning.xczx_plus_learning_model.Dao.XcChooseCourse;
import com.learning.xczx_plus_learning_model.Dao.XcCourseTables;
import com.learning.xczx_plus_learning_service.mapper.XcChooseCourseMapper;
import com.learning.xczx_plus_learning_service.mapper.XcCourseTablesMapper;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Receive {

    @Autowired
    XcCourseTablesMapper XcCourseTablesMapper;
    @Autowired
    XcChooseCourseMapper XcChooseCourseMapper;

    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(name = "xczx",type = "direct"),
            value = @Queue(name = "xczx_learning_orders"),
            key={"change"}
    ))
    public void Change(String id){
        XcChooseCourse xcChooseCourse = XcChooseCourseMapper.selectById(id);
        xcChooseCourse.setStatus("701001");
        XcChooseCourseMapper.updateById(xcChooseCourse);
        XcCourseTables XcCourseTables=new XcCourseTables();
        BeanUtils.copyProperties(xcChooseCourse,XcCourseTables);
        XcCourseTables.setChooseCourseId(xcChooseCourse.getId());
        XcCourseTables.setCourseType(xcChooseCourse.getOrderType());
        XcCourseTablesMapper.insert(XcCourseTables);
    }
}
