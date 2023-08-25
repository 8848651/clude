package com.contest.xczx_plus_content_service.content.service.Interface;


import com.contest.xczx_plus_content_model.content.Pto.TreeCourseCategoryDto;

import java.util.List;

public interface CourseCategoryService {

   List<TreeCourseCategoryDto> queryTreeNodes(String id);

   List<TreeCourseCategoryDto> queryTreeNode();
}
