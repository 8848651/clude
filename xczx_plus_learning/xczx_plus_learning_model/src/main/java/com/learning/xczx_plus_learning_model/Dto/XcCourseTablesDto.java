package com.learning.xczx_plus_learning_model.Dto;
import com.learning.xczx_plus_learning_model.Dao.XcCourseTables;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class XcCourseTablesDto extends XcCourseTables {
    //学习资格，[{"code":"702001","desc":"正常学习"},
    // {"code":"702002","desc":"没有选课或选课后没有支付"},
    // {"code":"702003","desc":"已过期需要申请续期或重新支付"}]
    public String learnStatus;
    public XcCourseTablesDto(){}
    public XcCourseTablesDto(XcCourseTables xcCourseTables) {
        super();
        this.setId(xcCourseTables.getId());
        this.setChooseCourseId(xcCourseTables.getChooseCourseId());
        this.setUserId(xcCourseTables.getUserId());
        this.setCourseId(xcCourseTables.getCourseId());
        this.setCompanyId(xcCourseTables.getCompanyId());
        this.setCourseName(xcCourseTables.getCourseName());
        this.setCourseType(xcCourseTables.getCourseType());
        this.setCreateDate(xcCourseTables.getCreateDate());
        this.setValidtimeStart(xcCourseTables.getValidtimeStart());
        this.setValidtimeEnd(xcCourseTables.getValidtimeEnd());
        this.setUpdateDate(xcCourseTables.getUpdateDate());
        this.setRemarks(xcCourseTables.getRemarks());
        this.learnStatus = null;
    }
}
