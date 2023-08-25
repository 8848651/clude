package com.contest.xczx_plus_content_model.content.Pto;

import com.contest.xczx_plus_content_model.content.Dao.CourseCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
//目录选择类
public class TreeCourseCategoryDto extends CourseCategory implements Serializable {
    List<TreeCourseCategoryDto> childrenTreeNodes;
    public TreeCourseCategoryDto(CourseCategory cc) {
        super(cc.getId(), cc.getName(), cc.getLabel(), cc.getParentid(), cc.getIsShow(), cc.getOrderby(), cc.getIsLeaf());
    }
}
