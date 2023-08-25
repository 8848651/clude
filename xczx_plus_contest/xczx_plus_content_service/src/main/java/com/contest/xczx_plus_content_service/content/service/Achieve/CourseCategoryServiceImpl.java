package com.contest.xczx_plus_content_service.content.service.Achieve;

import com.contest.xczx_plus_content_model.content.Dao.CourseCategory;
import com.contest.xczx_plus_content_model.content.Pto.TreeCourseCategoryDto;
import com.contest.xczx_plus_content_service.content.service.Interface.CourseCategoryService;
import com.contest.xczx_plus_content_service.content.mapper.CourseCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    static final String zero="1";

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    /**
     * 根据id查树形表
     */
    @Override
    public List<TreeCourseCategoryDto> queryTreeNodes(String id) {
        List<CourseCategory> courseCategories = courseCategoryMapper.SelectCourseCategory(id);
        List<TreeCourseCategoryDto> CourseCategoryDtos=new ArrayList<>();
        for (CourseCategory CC:courseCategories) {CourseCategoryDtos.add(new TreeCourseCategoryDto(CC));}
        //引用传递，Map和List中的引用为同一对象
        Map<String, TreeCourseCategoryDto> mapTemp = CourseCategoryDtos.stream().filter(item->!id.equals(item.getId())).collect(Collectors.toMap(key -> key.getId(), value -> value, (key1, key2) -> key2));
        List<TreeCourseCategoryDto> categoryTreeDtos = new ArrayList<>();
        CourseCategoryDtos.stream().filter(item->!id.equals(item.getId())).forEach(item->{
            if(item.getParentid().equals(id)){
                categoryTreeDtos.add(item);
            }
            TreeCourseCategoryDto courseCategoryTreeDto = mapTemp.get(item.getParentid());
            if(courseCategoryTreeDto!=null){
                if(courseCategoryTreeDto.getChildrenTreeNodes() ==null){
                    courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<TreeCourseCategoryDto>());
                }
                courseCategoryTreeDto.getChildrenTreeNodes().add(item);
            }
        });
        return categoryTreeDtos;
    }

    @Override
    public List<TreeCourseCategoryDto> queryTreeNode() {
        List<CourseCategory> courseCategories = courseCategoryMapper.SelectCourseCategorys();
        List<TreeCourseCategoryDto> CourseCategoryDtos=new ArrayList<>();
        for (CourseCategory CC:courseCategories) {CourseCategoryDtos.add(new TreeCourseCategoryDto(CC));}
        Map<String, TreeCourseCategoryDto> mapTemp = CourseCategoryDtos.stream().collect(Collectors.toMap(key -> key.getId(), value -> value, (key1, key2) -> key2));
        List<TreeCourseCategoryDto> categoryTreeDtos = new ArrayList<>();
        CourseCategoryDtos.stream().forEach(item->{
            if(item.getParentid().equals(zero)){
                categoryTreeDtos.add(item);
            }
            TreeCourseCategoryDto courseCategoryTreeDto = mapTemp.get(item.getParentid());
            if(courseCategoryTreeDto!=null){
                if(courseCategoryTreeDto.getChildrenTreeNodes() ==null){
                    courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<TreeCourseCategoryDto>());
                }
                courseCategoryTreeDto.getChildrenTreeNodes().add(item);
            }
        });
        return categoryTreeDtos;
    }

}

