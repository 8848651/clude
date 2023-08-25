package com.contest.xczx_plus_content_service.content.service.Achieve;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.contest.xczx_plus_content_model.content.Dao.CourseBase;
import com.contest.xczx_plus_content_model.content.Dao.CourseCategory;
import com.contest.xczx_plus_content_model.content.Dao.CourseMarket;
import com.contest.xczx_plus_content_model.content.Pto.*;
import com.contest.xczx_plus_content_service.content.mapper.CourseBaseMapper;
import com.contest.xczx_plus_content_service.content.mapper.CourseCategoryMapper;
import com.contest.xczx_plus_content_service.content.mapper.CourseMarketMapper;
import com.contest.xczx_plus_content_service.content.service.Interface.CourseBaseInfoService;
import com.contest.xczx_plus_content_service.content.service.Interface.CourseTeacherService;
import com.contest.xczx_plus_content_service.content.service.Interface.TeachplanService;
import com.base.xczx_plus_base.base.model.PageParams;
import com.base.xczx_plus_base.base.model.PageResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {
    @Autowired
    private CourseBaseMapper courseBaseMapper;
    @Autowired
    private CourseMarketMapper courseMarketMapper;
    @Autowired
    private CourseCategoryMapper courseCategoryMapper;
    @Autowired
    private TeachplanService teachplanService;
    @Autowired
    private CourseTeacherService courseTeacherService;



    /**增加
     * ReceiveAddCourseDto和 ReturnAddCourseDto基本一样，不过ReceiveAddCourseDto需要进行一些非空判断
     * 以及返回值之间的区别
     */
    @Override
    public ReturnAddCourseDto addCourseBase(Long companyId, ReceiveAddCourseDto addCourseDto) {
        CourseBase courseBaseNew = new CourseBase();
        CourseMarket courseMarketNew = new CourseMarket();
        BeanUtils.copyProperties(addCourseDto, courseBaseNew);
        BeanUtils.copyProperties(addCourseDto,courseMarketNew);
        courseBaseNew.setCompanyId(companyId);
        courseBaseNew.setCreateDate(LocalDateTime.now());
        courseBaseNew.setAuditStatus("202002");
        courseBaseNew.setStatus("203001");
        courseBaseMapper.insert(courseBaseNew);
        Long courseId = courseBaseNew.getId();
        courseMarketNew.setId(courseId);
        courseMarketMapper.insert(courseMarketNew);
        return selectCourseBasebyId(courseId);
    }


    /**
     *删除课程信息
     */
    @Override
    public void deleteCourseBase(Long courseid){
        courseBaseMapper.deleteById(courseid);
        teachplanService.DeleteTeachplanPlus(courseid);
        courseTeacherService.DelectCourseTeacherPlus(courseid);
    }



    /**
     *修改课程信息
     */
    @Override
    public ReturnReviseCourseDto reviseCourseBase(Long companyid , ReceiveReviseCourseDto RRCD){
        CourseBase courseBaseNew = new CourseBase();
        CourseMarket courseMarketNew = new CourseMarket();
        BeanUtils.copyProperties(RRCD,courseBaseNew);
        BeanUtils.copyProperties(RRCD,courseMarketNew);
        courseBaseNew.setCreateDate(LocalDateTime.now());
        courseBaseMapper.updateById(courseBaseNew);
        CourseMarket courseMarket = courseMarketMapper.selectById(courseMarketNew);
        if (courseMarket == null) {
            courseMarketMapper.insert(courseMarketNew);
        }else{
            courseMarketMapper.updateById(courseMarketNew);
        }
        return selectCourseBasebyId(RRCD.getId());
    }

    /**
     * 根据页面和条件查询
     */
    @Override
    public PageResult<CourseBase> selectCourseBaseList(long aLong,PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()), CourseBase::getName, queryCourseParamsDto.getCourseName());
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()), CourseBase::getAuditStatus, queryCourseParamsDto.getAuditStatus());
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()), CourseBase::getStatus, queryCourseParamsDto.getPublishStatus());
        queryWrapper.eq(CourseBase::getCompanyId, aLong);
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);
        List<CourseBase> list = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(list, total, pageParams.getPageNo(), pageParams.getPageSize());
        return courseBasePageResult;
    }

    /**
     * 根据id查询特定课程信息
     */
    @Override
    public ReturnReviseCourseDto selectCourseBasebyId(Long id){
        ReturnReviseCourseDto returnCourseDto = new ReturnReviseCourseDto();
        CourseBase courseBase = courseBaseMapper.selectById(id);
        CourseMarket courseMarkets = courseMarketMapper.selectById(id);
        if(courseBase!=null) {
            BeanUtils.copyProperties(courseBase, returnCourseDto);
        }
        if(courseMarkets!=null) {
            BeanUtils.copyProperties(courseMarkets, returnCourseDto);
        }
        System.out.println(courseBase.getSt());
        CourseCategory courseCategoryBySt = courseCategoryMapper.selectById(courseBase.getSt());
        System.out.println(courseCategoryBySt.getName());
        returnCourseDto.setStName(courseCategoryBySt.getName());
        CourseCategory courseCategoryByMt = courseCategoryMapper.selectById(courseBase.getMt());
        returnCourseDto.setMtName(courseCategoryByMt.getName());
        return returnCourseDto;
    }
}


