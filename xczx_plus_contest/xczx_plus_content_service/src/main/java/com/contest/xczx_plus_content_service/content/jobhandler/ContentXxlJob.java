package com.contest.xczx_plus_content_service.content.jobhandler;

import com.contest.xczx_plus_content_model.content.Pto.ReturnReviseCourseDto;
import com.contest.xczx_plus_content_service.content.config.MultipartSupportConfig;
import com.contest.xczx_plus_content_service.content.feign.feignclient1;
import com.contest.xczx_plus_content_service.content.feign.feignclient2;
import com.contest.xczx_plus_content_service.content.service.Interface.CourseBaseInfoService;
import com.messdk.xczx_plus_messdk.po.MqMessage;
import com.messdk.xczx_plus_messdk.service.MessageProcessAbstract;
import com.messdk.xczx_plus_messdk.service.MqMessageService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class ContentXxlJob extends MessageProcessAbstract {

    @Autowired
    feignclient1 feignclient1;
    @Autowired
    feignclient2 feignclient2;
    @Autowired
    PageStatization PageStatization;
    @Autowired
    CourseBaseInfoService CourseBaseInfoService;

    @XxlJob("ContentXxljob")
    public void VideoChange() throws InterruptedException {
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        this.process(shardIndex,shardTotal,"course_publish",1);
    }

    @Override
    public boolean execute(MqMessage mqMessage){
        try {
            Boolean aBoolean1 = Statization(mqMessage);
            System.out.println("aBoolean1:"+aBoolean1);
            Boolean aBoolean2 = EsDatabase(mqMessage);
            System.out.println("aBoolean2:"+aBoolean2);
            if (aBoolean1&&aBoolean2) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //任务静态化
    private Boolean Statization(MqMessage mqMessage) throws IOException, TemplateException {
        long parseLong = Long.parseLong(mqMessage.getBusinessKey1());
        MqMessageService mqMessageService = this.getMqMessageService();
        int stageOne = mqMessageService.getStageOne(mqMessage.getId());
        if (stageOne>0) {
            return true;
        }
        //开始静态化
        String path = "course/" + parseLong + ".html";
        File statization = PageStatization.Statization(parseLong);
        MultipartFile multipartFile= MultipartSupportConfig.getMultipartFile(statization);
        System.out.println(statization.getAbsolutePath());
        String upload = feignclient1.Upload(multipartFile, path);
        if (upload != null) {
            mqMessageService.completedStageOne(mqMessage.getId());
            return true;
        }
        return false;
    }

    //任务向es写
    private Boolean EsDatabase(MqMessage mqMessage) throws IOException {
        long parseLong = Long.parseLong(mqMessage.getBusinessKey1());
        MqMessageService mqMessageService = this.getMqMessageService();
        int stageTwo = mqMessageService.getStageTwo(mqMessage.getId());
        if (stageTwo>0) {
            return true;
        }
        //开始写入
        ReturnReviseCourseDto dto = CourseBaseInfoService.selectCourseBasebyId(parseLong);
        Boolean add = feignclient2.add(dto);
        if (add!=null&&add) {
            mqMessageService.completedStageTwo(mqMessage.getId());
            return true;
        }
        return false;
    }
}
