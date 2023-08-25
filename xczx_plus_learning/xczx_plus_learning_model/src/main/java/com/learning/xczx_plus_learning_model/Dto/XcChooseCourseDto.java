package com.learning.xczx_plus_learning_model.Dto;
import com.learning.xczx_plus_learning_model.Dao.XcChooseCourse;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class XcChooseCourseDto extends XcChooseCourse {

    //学习资格
    public String learnStatus;

    public XcChooseCourseDto(XcChooseCourse xcChooseCourse) {
        super();
        this.setId(xcChooseCourse.getId());
        this.setCourseId(xcChooseCourse.getCourseId());
        this.setCourseName(xcChooseCourse.getCourseName());
        this.setUserId(xcChooseCourse.getUserId());
        this.setCompanyId(xcChooseCourse.getCompanyId());
        this.setOrderType(xcChooseCourse.getOrderType());
        this.setCreateDate(xcChooseCourse.getCreateDate());
        this.setValidDays(xcChooseCourse.getValidDays());
        this.setCoursePrice(xcChooseCourse.getCoursePrice());
        this.setStatus(xcChooseCourse.getStatus());
        this.setValidtimeStart(xcChooseCourse.getValidtimeStart());
        this.setValidtimeEnd(xcChooseCourse.getValidtimeEnd());
        this.setRemarks(xcChooseCourse.getRemarks());
        this.learnStatus = null;
    }

}
