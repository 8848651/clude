package com.contest.xczx_plus_content_model.content.Pto;


import com.contest.xczx_plus_content_model.content.Dao.Teachplan;
import com.contest.xczx_plus_content_model.content.Dao.TeachplanMedia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//返回课程目录类
public class TreeTeachplanDto extends Teachplan {
    private TeachplanMedia teachplanMedia;
    List<TreeTeachplanDto> teachPlanTreeNodes;
    public TreeTeachplanDto(Teachplan TT){
        super(TT.getId(), TT.getPname(), TT.getParentid(), TT.getGrade(), TT.getMediaType(), TT.getStartTime(),TT.getEndTime(), TT.getDescription(), TT.getTimelength(), TT.getOrderby(), TT.getCourseId(), TT.getCoursePubId(), TT.getStatus(), TT.getIsPreview(), TT.getCreateDate(), TT.getChangeDate());
    }
}
