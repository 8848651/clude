package com.contest.xczx_plus_content_service.content.service.Achieve;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.contest.xczx_plus_content_model.content.Dao.Teachplan;
import com.contest.xczx_plus_content_model.content.Dao.TeachplanMedia;
import com.contest.xczx_plus_content_model.content.Pto.TreeTeachplanDto;
import com.contest.xczx_plus_content_service.content.mapper.TeachplanMapper;
import com.contest.xczx_plus_content_service.content.mapper.TeachplanMediaMapper;
import com.contest.xczx_plus_content_service.content.service.Interface.TeachplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeachplanServiceImpl implements TeachplanService {
    static final Long zero=0L;
    @Autowired
    private TeachplanMapper teachplanMapper;
    @Autowired
    private TeachplanMediaMapper teachplanMediaMapper;

    /**
     *增加修改目录信息
     */
    @Override
    public void AddTeachplan(Teachplan RTD){
        if(RTD.getId()==null){
            teachplanMapper.insert(RTD);
        }else{
            teachplanMapper.updateById(RTD);
        }
    }

    /**
     * 移动
     */
    @Override
    public void Move(Long id, Boolean move){
        //根据id将该课程的所有目录都查出来
        Teachplan teachplan1 = teachplanMapper.selectById(id);
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId,teachplan1.getCourseId());
        List<Teachplan> CourseTeachDtos=teachplanMapper.selectList(queryWrapper);
         if(teachplan1.getParentid().equals(zero)){
             //如果要移动一级目录
            Movesparent(teachplan1,CourseTeachDtos,move);
        }else{
             //如果要移动二级目录
             Movesson(teachplan1,CourseTeachDtos,move);
         }

    }

    /**
     * 移动一级目录
     * @param teachplan1 要移动的目录
     * @param CourseTeachDtos 该课程的所有目录
     * @param move 向上还是向下移动
     */
    private void Movesparent(Teachplan teachplan1,List<Teachplan> CourseTeachDtos,Boolean move){
        List<Teachplan> collect = new ArrayList<>();
        List<Teachplan> MoveTemp = new ArrayList<>();
        //zero=0L  如果不是一级目录退出
        if (!teachplan1.getParentid().equals(zero)) {
            return;
        }
        //将所有的一级目录找出来放到MoveTeachplan中
        CourseTeachDtos.stream().forEach(item->{
            if(item.getParentid().equals(zero)){
                collect.add(item);
            }
        });
        //对一级目录进行排序
        Collections.sort(collect, (Teachplan o1, Teachplan o2) -> (int) (o1.getId() - o2.getId()));
        //找出list中要排序所在索引
        int index = collect.indexOf(teachplan1);
        if(move){index++;}else {index--;}
        if(index<0||index>= collect.size()){return;}
        Teachplan teachplan2 = collect.get(index);
        List<Teachplan> MoveTemps = new ArrayList<>();
        List<Teachplan> MoveTempl = new ArrayList<>();
        //交换两者子目录
        CourseTeachDtos.stream().forEach(item->{
            if(item.getParentid().equals(teachplan1.getId())){
                //MoveTempl.add(new Teachplan(item));
                item.setParentid(teachplan2.getId());
                MoveTemps.add(item);
            }else if(item.getParentid().equals(teachplan2.getId())){
                //MoveTempl.add(new Teachplan(item));
                item.setParentid(teachplan1.getId());
                MoveTemps.add(item);
            }
        });
        /*System.out.println("修改前：");
        System.out.println(teachplan1.getId()+" "+teachplan1.getPname());
        System.out.println(teachplan2.getId()+" "+teachplan2.getPname());
        for (int i = 0; i <MoveTempl.size(); i++) {
            Teachplan teachplan3 = MoveTempl.get(i);
            System.out.println(teachplan3.getId()+" "+teachplan3.getPname()+" "+teachplan3.getParentid());
        }*/
        Long ids1 = teachplan1.getId();
        Long ids2 = teachplan2.getId();
        teachplan1.setId(ids2);
        teachplan2.setId(ids1);
        MoveTemp.add(teachplan1);
        MoveTemp.add(teachplan2);
        MoveTemp.addAll(MoveTemps);
       /* System.out.println("修改后：");
        System.out.println(teachplan1.getId()+" "+teachplan1.getPname());
        System.out.println(teachplan2.getId()+" "+teachplan2.getPname());
        for (int i = 0; i < MoveTemps.size(); i++) {
            Teachplan teachplan4 = MoveTemps.get(i);
            System.out.println(teachplan4.getId()+" "+teachplan4.getPname()+" "+teachplan4.getParentid());
        }*/
        //System.out.println(MoveTemp);
        //批量更新
        teachplanMapper.UpdateBatch(MoveTemp);
    }

    /**
     * 移动二级目录
     * @param teachplan1  要移动的目录
     * @param CourseTeachDtos 该课程的所有目录
     * @param move 向上还是向下移动
     */
    private void Movesson(Teachplan teachplan1,List<Teachplan> CourseTeachDtos,Boolean move){
        List<Teachplan> MoveTeachplan = new ArrayList<>();
        List<Teachplan> MoveTemp = new ArrayList<>();
        Map<Long, Teachplan> mapTemp =  CourseTeachDtos.stream().collect(Collectors.toMap(key -> key.getId(), value -> value, (key1, key2) -> key2));
        //找出teachplan1父目录下的所有目录
        CourseTeachDtos.stream().forEach(item->{
            Long parentid = item.getParentid();
            Teachplan treeTeachplanDto=null;
            //为teachplan1父目录下除子目录的所有目录
            if(!parentid.equals(teachplan1.getParentid())){
                Loop:while (!parentid.equals(teachplan1.getParentid())){
                    treeTeachplanDto= mapTemp.get(parentid);
                    if(treeTeachplanDto==null){break Loop;}
                    parentid=treeTeachplanDto.getParentid();
                }
            }else{
                //为teachplan1兄弟目录
                MoveTeachplan.add(item);
            }
            //以非null形式结束循环为该父目录的子目录
            if(treeTeachplanDto!=null){
                MoveTeachplan.add(item);
            }
        });
        List<Teachplan> collect = MoveTeachplan.stream()
                .filter(item -> item.getCourseId().equals(teachplan1.getCourseId()))
                .collect(Collectors.toList());
        Collections.sort(collect, (Teachplan o1, Teachplan o2) -> (int) (o1.getId() - o2.getId()));
        int index = collect.indexOf(teachplan1);
        if(move){index++;}else {index--;}
        if(index<0||index>= collect.size()){return;}
        Teachplan teachplan2 = collect.get(index);
        TeachplanMedia teachplanMedia1 = MoveMedia(teachplan1, teachplan2);
        TeachplanMedia teachplanMedia2 = MoveMedia(teachplan2, teachplan1);
        Long ids1 = teachplan1.getId();
        Long ids2 = teachplan2.getId();
        teachplan1.setId(ids2);
        teachplan2.setId(ids1);
        MoveTemp.add(teachplan1);
        MoveTemp.add(teachplan2);
        //只需交换id无需交换子目录
        if(teachplanMedia1!=null&&teachplanMedia2!=null) {
            teachplanMediaMapper.updateById(teachplanMedia1);
            teachplanMediaMapper.updateById(teachplanMedia2);
        }
        teachplanMapper.UpdateBatch(MoveTemp);
    }


    //交换两个二级目录的媒体资源
    private TeachplanMedia MoveMedia(Teachplan teachplan1,Teachplan teachplan2){
        LambdaQueryWrapper<TeachplanMedia> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TeachplanMedia::getTeachplanId,teachplan1.getId());
        TeachplanMedia teachplanMedia1 = teachplanMediaMapper.selectOne(queryWrapper);
        if(teachplanMedia1==null){return null;}
        teachplanMedia1.setTeachplanId(teachplan2.getId());
        return teachplanMedia1;
    }


    /**
     * 根据目录id删除目录
     * @param id
     */
    @Override
    public void DeleteTeachplan(Long id){
        //查出该课程所有目录
        Teachplan teachplan1 = teachplanMapper.selectById(id);
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId,teachplan1.getCourseId());
        List<Teachplan> CourseTeachDtos=teachplanMapper.selectList(queryWrapper);
        Map<Long, Teachplan> mapTemp =  CourseTeachDtos.stream().collect(Collectors.toMap(key -> key.getId(), value -> value, (key1, key2) -> key2));
        List<Teachplan> TempTeachplan;
        //如果是二级及其以下目录
        if(!teachplan1.getParentid().equals(zero)) {
            TempTeachplan = new ArrayList<>();
            CourseTeachDtos.stream().forEach(item -> {
                if (item.getParentid().equals(id)) {
                    TempTeachplan.add(item);
                }
            });
            TempTeachplan.stream().forEach(item -> {
                item.setParentid(teachplan1.getParentid());
            });
            TempTeachplan.add(teachplan1);
            DeleteList(TempTeachplan);
            return;
        }
        //如果是一级目录
        TempTeachplan = new ArrayList<>();
        CourseTeachDtos.stream().forEach(item->{
            Long parentid = item.getParentid();
            Teachplan treeTeachplanDto=null;
            if(!parentid.equals(id)){
                Loop:while (!parentid.equals(id)){
                    treeTeachplanDto= mapTemp.get(parentid);
                    if(treeTeachplanDto==null){break Loop;}
                    parentid=treeTeachplanDto.getParentid();
                }
            }else{
                TempTeachplan.add(item);
            }
            if(treeTeachplanDto!=null){
                TempTeachplan.add(item);
            }
        });
        TempTeachplan.add(teachplan1);
        DeleteList(TempTeachplan);
    }

    /**
     * 批量删除对应的目录及其视频信息
     * @param TempTeachplan
     */
    public void DeleteList(List<Teachplan> TempTeachplan){
        LambdaQueryWrapper<TeachplanMedia> queryWrapper1 = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Teachplan> queryWrapper2 = new LambdaQueryWrapper<>();
        TempTeachplan.stream().forEach(item -> {
            queryWrapper1.eq(TeachplanMedia::getTeachplanId,item.getId());
            queryWrapper1.or();
            queryWrapper2.eq(Teachplan::getId,item.getId());
            queryWrapper2.or();
        });
        teachplanMediaMapper.delete(queryWrapper1);
        teachplanMapper.delete(queryWrapper2);
    }

    /**
     *根据course_id批量删除
     */
    @Override
    public void DeleteTeachplanPlus(Long course_id){
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId,course_id);
        List<Teachplan> CourseTeachDtos=teachplanMapper.selectList(queryWrapper);
        DeleteList(CourseTeachDtos);
    }

    /**
     * 根据courseid查询课程信息
     */
    @Override
    public List<TreeTeachplanDto> Selectbycourseid(Long courseid) {
        //查出该课成所有目录
        List<TreeTeachplanDto> CourseTeachDtos=teachplanMapper.SelectTeachplan(courseid);
        Map<Long, TreeTeachplanDto> mapTemp =  CourseTeachDtos.stream().collect(Collectors.toMap(key -> key.getId(), value -> value, (key1, key2) -> key2));
        List<TreeTeachplanDto> categoryTreeDtos = new ArrayList<>();
        CourseTeachDtos.stream().forEach(item->{
            //添加一级目录
            if(item.getParentid().equals(zero)){
                item.setTeachplanMedia(null);
                categoryTreeDtos.add(item);
            }else {
                //添加二级目录
                Long parentid = item.getParentid();
                TreeTeachplanDto treeTeachplanDto=null;
                Loop:while (!parentid.equals(zero)){
                    treeTeachplanDto= mapTemp.get(parentid);
                    if(treeTeachplanDto==null){break Loop;}
                    parentid=treeTeachplanDto.getParentid();
                }
                if (treeTeachplanDto != null) {
                    if (treeTeachplanDto.getTeachPlanTreeNodes() == null) {
                        treeTeachplanDto.setTeachPlanTreeNodes(new ArrayList<TreeTeachplanDto>());
                    }
                    treeTeachplanDto.getTeachPlanTreeNodes().add(item);
                }
            }
        });
        return categoryTreeDtos;
    }

}

