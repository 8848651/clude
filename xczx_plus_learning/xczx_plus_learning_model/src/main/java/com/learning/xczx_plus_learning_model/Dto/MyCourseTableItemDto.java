package com.learning.xczx_plus_learning_model.Dto;

import com.learning.xczx_plus_learning_model.Dao.XcCourseTables;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
@Data
@ToString
public class MyCourseTableItemDto extends XcCourseTables {

    /**
     * 最近学习时间
     */
    private LocalDateTime learnDate;

    /**
     * 学习时长
     */
    private Long learnLength;

    /**
     * 章节id
     */
    private Long teachplanId;

    /**
     * 章节名称
     */
    private String teachplanName;


}
