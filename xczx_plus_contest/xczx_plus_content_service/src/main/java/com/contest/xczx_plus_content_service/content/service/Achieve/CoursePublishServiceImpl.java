package com.contest.xczx_plus_content_service.content.service.Achieve;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.contest.xczx_plus_content_model.content.Dao.*;
import com.contest.xczx_plus_content_model.content.Pto.TreeTeachplanDto;
import com.contest.xczx_plus_content_service.content.mapper.*;
import com.contest.xczx_plus_content_service.content.service.Interface.CourseTeacherService;
import com.base.xczx_plus_base.base.exception.XczxPlusException;
import com.contest.xczx_plus_content_service.content.service.Interface.CoursePublishService;
import com.contest.xczx_plus_content_service.content.service.Interface.TeachplanService;
import com.messdk.xczx_plus_messdk.po.MqMessage;
import com.messdk.xczx_plus_messdk.service.MqMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class CoursePublishServiceImpl implements CoursePublishService {
     @Autowired
     TeachplanService teachplanService;
     @Autowired
     CourseTeacherService courseTeacherService;
     @Autowired
     CourseBaseMapper courseBaseMapper;
     @Autowired
     CourseMarketMapper courseMarketMapper;
     @Autowired
     CoursePublishPreMapper coursePublishPreMapper;
     @Autowired
     CoursePublishMapper coursePublishMapper;
     @Autowired
    CourseCategoryMapper CourseCategoryMapper;
     @Autowired
     MqMessageService mqMessageService;



    @Override
    public void CommitAudit(Long companyId, Long courseId) {
        //根据课程id查出课程
        CourseBase courseBaseInfo = courseBaseMapper.selectById(courseId);
        if (courseBaseInfo == null) {
            XczxPlusException.cast("课程找不到");
        }
        String auditStatus = courseBaseInfo.getAuditStatus();
        if (auditStatus.equals("202003")) {
            XczxPlusException.cast("课程正在审核");
        }
        String pic = courseBaseInfo.getPic();
        List<TreeTeachplanDto> teachplanTree = teachplanService.Selectbycourseid(courseId);
        //将营销表和目录表插入待审核表
        CoursePublishPre coursePublishPre = new CoursePublishPre();
        BeanUtils.copyProperties(courseBaseInfo,coursePublishPre);
        coursePublishPre.setCompanyId(companyId);
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        String courseMarketJson = JSON.toJSONString(courseMarket);
        coursePublishPre.setMarket(courseMarketJson);
        BeanUtils.copyProperties(courseMarket,coursePublishPre);
        String teachplanTreeJson = JSON.toJSONString(teachplanTree);
        coursePublishPre.setTeachplan(teachplanTreeJson);
        List<CourseTeacher> courseTeachers = courseTeacherService.SelectCourseTeacher(courseId);
        String courseTeachersJson = JSON.toJSONString(courseTeachers);
        coursePublishPre.setTeachers(courseTeachersJson);
        coursePublishPre.setCreateDate(LocalDateTime.now());
        if (coursePublishPre.getMtName()==null) {
            CourseCategory courseCategory = CourseCategoryMapper.selectById(coursePublishPre.getMt());
            String MtName = courseCategory.getName();
            coursePublishPre.setMtName(MtName);
        }
        if (coursePublishPre.getStName()==null) {
            CourseCategory courseCategory = CourseCategoryMapper.selectById(coursePublishPre.getSt());
            String StName = courseCategory.getName();
            coursePublishPre.setStName(StName);
        }
        CoursePublishPre coursePublishPreObj = coursePublishPreMapper.selectById(courseId);
        if(coursePublishPreObj==null){
            coursePublishPreMapper.insert(coursePublishPre);
        }else {
            coursePublishPreMapper.updateById(coursePublishPre);
        }
        //更新课程基本信息表的审核状态为已提交
        courseBaseInfo.setAuditStatus("202003");
        courseBaseMapper.updateById(courseBaseInfo);
        test(courseId);
    }

    //进行审核
    public void test(Long courseId){
        CourseBase courseBaseInfo = courseBaseMapper.selectById(courseId);
        courseBaseInfo.setAuditStatus("202004");
        courseBaseMapper.updateById(courseBaseInfo);
    }


    @Override
    public void CommitPublish(Long companyId, Long courseId) {
        //查询预发布表
        CoursePublishPre coursePublishPre = coursePublishPreMapper.selectById(courseId);
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if(coursePublishPre == null){
            XczxPlusException.cast("课程没有审核记录，无法发布");
        }
        String status = courseBase.getAuditStatus();
        if(!status.equals("202004")){
            XczxPlusException.cast("课程没有审核通过不允许发布");
        }
        //向课程发布表写入数据
        CoursePublish coursePublish = new CoursePublish();
        BeanUtils.copyProperties(coursePublishPre,coursePublish);
        coursePublish.setStatus("203002");
        courseBase.setStatus("203002");
        courseBase.setAuditStatus("202002");
        courseBaseMapper.updateById(courseBase);
        CoursePublish coursePublishObj = coursePublishMapper.selectById(courseId);
        if(coursePublishObj == null){
            coursePublishMapper.insert(coursePublish);
        }else{
            coursePublishMapper.updateById(coursePublish);
        }
        //将该字段插入到MqMessage表中以待后续处理
        mqMessageService.addMessage("course_publish", String.valueOf(courseId), null, null);
        //将预发布表数据删除
        coursePublishPreMapper.deleteById(courseId);
    }


    @Override
    public CoursePublish SelectCoursePublish(Long id){
        CoursePublish coursePublish = coursePublishMapper.selectById(id);
        return coursePublish;
    }

}
