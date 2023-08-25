package com.contest.xczx_plus_content_service.content.service.Interface;

import com.contest.xczx_plus_content_model.content.Dao.Teachplan;
import com.contest.xczx_plus_content_model.content.Pto.TreeTeachplanDto;

import java.util.List;

public interface TeachplanService {

    /**
     *增加修改目录信息
     */
    void AddTeachplan(Teachplan RTD);

    /**
     *根据course_id批量删除，删除一个课程的目录
     */
    void DeleteTeachplanPlus(Long course_id);

    /**
     * 移动目录
     */
    void Move(Long id,Boolean move);

    /**
     * 根据目录id删除目录
     * @param id
     */
    void DeleteTeachplan(Long id);

    /**
     * 根据courseid查询课程信息
     */
    List<TreeTeachplanDto> Selectbycourseid(Long courseid);

}

