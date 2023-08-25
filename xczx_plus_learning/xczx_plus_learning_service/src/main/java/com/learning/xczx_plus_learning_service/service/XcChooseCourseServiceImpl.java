package com.learning.xczx_plus_learning_service.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.xczx_plus_base.base.model.PageResult;
import com.base.xczx_plus_base.base.model.RestResponse;
import com.contest.xczx_plus_content_model.content.Dao.CoursePublish;
import com.learning.xczx_plus_learning_model.Dao.XcChooseCourse;
import com.learning.xczx_plus_learning_model.Dao.XcCourseTables;
import com.learning.xczx_plus_learning_model.Dto.MyCourseTableParams;
import com.learning.xczx_plus_learning_model.Dto.XcChooseCourseDto;
import com.learning.xczx_plus_learning_model.Dto.XcCourseTablesDto;
import com.learning.xczx_plus_learning_service.feignclient.feignclient1;
import com.learning.xczx_plus_learning_service.feignclient.feignclient2;
import com.learning.xczx_plus_learning_service.mapper.XcChooseCourseMapper;
import com.learning.xczx_plus_learning_service.mapper.XcCourseTablesMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class XcChooseCourseServiceImpl {

    @Autowired
    feignclient1 feignclient1;
    @Autowired
    XcCourseTablesMapper XcCourseTablesMapper;
    @Autowired
    XcChooseCourseMapper XcChooseCourseMapper;

    //添加课程到选课表
    public XcChooseCourseDto AddCourseService(String userid, Long courseid) {
        CoursePublish publish = feignclient1.getCoursepublish(courseid);
        String charge = publish.getCharge();
        LambdaQueryWrapper<XcChooseCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(XcChooseCourse::getUserId, userid);
        queryWrapper.eq(XcChooseCourse::getCourseId, courseid);
        List<XcChooseCourse> xcChooseCourses = XcChooseCourseMapper.selectList(queryWrapper);
        if (xcChooseCourses.size() > 0) {
            XcChooseCourse xcChooseCourse = xcChooseCourses.get(0);
            XcChooseCourseDto CourseDto = new XcChooseCourseDto(xcChooseCourse);
            return CourseDto;
        }

        XcChooseCourse chooseCourse = new XcChooseCourse();
        chooseCourse.setCourseId(courseid);
        chooseCourse.setCourseName(publish.getName());
        chooseCourse.setUserId(userid);
        chooseCourse.setCompanyId(publish.getCompanyId());
        chooseCourse.setCreateDate(LocalDateTime.now());
        chooseCourse.setCoursePrice(publish.getPrice());
        chooseCourse.setValidDays(365);
        chooseCourse.setOrderType("700001");
        chooseCourse.setStatus("701001");
        chooseCourse.setValidtimeStart(LocalDateTime.now());
        chooseCourse.setValidtimeEnd(LocalDateTime.now().plusDays(365));

        if (charge.equals("201000")) {
            XcCourseTables XcCourseTables = new XcCourseTables();
            BeanUtils.copyProperties(chooseCourse, XcCourseTables);
            XcChooseCourseMapper.insert(chooseCourse);
            XcCourseTables.setChooseCourseId(chooseCourse.getId());
            XcCourseTables.setCourseType(chooseCourse.getOrderType());
            XcCourseTablesMapper.insert(XcCourseTables);
            XcChooseCourseDto CourseDto = new XcChooseCourseDto(chooseCourse);
            CourseDto.setLearnStatus("702001");
            return CourseDto;
        }

        //700002:付费 700001:免费
        //701002:待支付 701001 支付成功
        //学习资格：702001 有 702002: 没有选课选课没有支付 702003：已过期
        //默认免费选课成功
        chooseCourse.setOrderType("700002");
        chooseCourse.setStatus("701002");
        XcChooseCourseMapper.insert(chooseCourse);
        XcChooseCourseDto CourseDto = new XcChooseCourseDto(chooseCourse);
        CourseDto.setLearnStatus("702002");
        return CourseDto;
    }

    //查看其学习资格
    public XcCourseTablesDto SelectCourseService(String userid, Long courseid) {
        LambdaQueryWrapper<XcCourseTables> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(XcCourseTables::getUserId, userid);
        queryWrapper.eq(XcCourseTables::getCourseId, courseid);
        XcCourseTables tables = XcCourseTablesMapper.selectOne(queryWrapper);
        if (tables != null) {
            XcCourseTablesDto XcCourseTablesDto = new XcCourseTablesDto(tables);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime validtimeEnd = tables.getValidtimeEnd();
            if (now.isBefore(validtimeEnd)) {
                XcCourseTablesDto.setLearnStatus("702001");
            } else {
                XcChooseCourseMapper.deleteById(tables.getChooseCourseId());
                XcCourseTablesMapper.deleteById(tables);
                XcCourseTablesDto.setLearnStatus("702003");
            }
            return XcCourseTablesDto;
        }
        LambdaQueryWrapper<XcChooseCourse> queryWrappers = new LambdaQueryWrapper<>();
        queryWrappers.eq(XcChooseCourse::getUserId, userid);
        queryWrappers.eq(XcChooseCourse::getCourseId, courseid);
        List<XcChooseCourse> xcChooseCourses = XcChooseCourseMapper.selectList(queryWrappers);
        if (xcChooseCourses.size() > 0) {
            XcChooseCourse course = xcChooseCourses.get(0);
            XcCourseTablesDto XcCourseTablesDto = new XcCourseTablesDto();
            BeanUtils.copyProperties(course, XcCourseTablesDto);
            XcCourseTablesDto.setLearnStatus("702002");
            return XcCourseTablesDto;
        }
        CoursePublish publish = feignclient1.getCoursepublish(courseid);
        String charge = publish.getCharge();
        XcCourseTablesDto XcCourseTablesDto = new XcCourseTablesDto();
        XcCourseTablesDto.setCourseId(courseid);
        XcCourseTablesDto.setCourseName(publish.getName());
        XcCourseTablesDto.setUserId(userid);
        XcCourseTablesDto.setCompanyId(publish.getCompanyId());
        XcCourseTablesDto.setCreateDate(LocalDateTime.now());
        if (charge.equals("201000")) {
            XcCourseTablesDto.setCourseType("700001");
        } else {
            XcCourseTablesDto.setCourseType("700002");
        }
        XcCourseTablesDto.setValidtimeStart(LocalDateTime.now());
        XcCourseTablesDto.setValidtimeEnd(LocalDateTime.now().plusDays(365));
        XcCourseTablesDto.setLearnStatus("702002");

        return XcCourseTablesDto;
    }
    
    //查询用户所有课程
    public PageResult<XcCourseTables> SelectCourse(MyCourseTableParams Params){
        LambdaQueryWrapper<XcCourseTables> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(XcCourseTables::getUserId,Params.getUserId());
        Page<XcCourseTables> page = new Page<>(Params.getPage(), Params.getSize());
        Page<XcCourseTables> pageResult = XcCourseTablesMapper.selectPage(page, queryWrapper);
        List<XcCourseTables> list = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageResult<XcCourseTables> courseBasePageResult = new PageResult<>(list, total,Params.getPage(),Params.getSize());
        return courseBasePageResult;
    }
}
