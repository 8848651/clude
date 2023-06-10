package Mybatis.Dome;
import Mybatis.Mapper.CRUDMapper;
import Mybatis.Mybatis_GJL.User;
import Mybatis.Mybatis_GJL.Mappersqlsession;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MybatisDome {
    @Test
    //查询所有
    public void test1() throws IOException {
        SqlSession SS = Mappersqlsession.Sqlsessions();
        CRUDMapper CM = SS.getMapper(CRUDMapper.class);
        List<User> ST =CM.Selectall();
        System.out.println("---------------------------");
        System.out.println(ST);
        System.out.println("---------------------------");
        SS.close();
    }
    @Test
    //查询
    public void test2() throws IOException {
        String StudentNumber="209044524";
        User user = new User(StudentNumber);
        SqlSession SS = Mappersqlsession.Sqlsessions();
        CRUDMapper CM = SS.getMapper(CRUDMapper.class);
        User ST =CM.Multicondition_Select_JZ(user);
        System.out.println("---------------------------");
        System.out.println(ST);
        System.out.println("---------------------------");
        SS.close();
    }
    @Test
    //添加
    public void test3() throws IOException {
        User user = new User("2090445211","李一","8848");
        SqlSession SS = Mappersqlsession.Sqlsessions();
        CRUDMapper CM = SS.getMapper(CRUDMapper.class);
        CM.insert(user);
        //提交到数据库中
        SS.commit();
        SS.close();
    }
    @Test
    //修改
    public void test4() throws IOException {
        User user = new User("2090445211");
        user.setName("王五");
        SqlSession SS = Mappersqlsession.Sqlsessions();
        CRUDMapper CM = SS.getMapper(CRUDMapper.class);
        int staticupdate = CM.dynamicupdate(user);
        System.out.println(staticupdate);
        //提交到数据库中
        SS.commit();
        SS.close();
    }
    @Test
    //删除一个
    public void test5() throws IOException {
       String StudentNumber = "2090445211";
        SqlSession SS = Mappersqlsession.Sqlsessions();
        CRUDMapper CM = SS.getMapper(CRUDMapper.class);
        CM.deleteone(StudentNumber);
        //提交到数据库中
        SS.commit();
        SS.close();
    }
    @Test
    //删除多个
    public void test6() throws IOException {
        String[] StudentNumber = {"20904458","20904457"};
        SqlSession SS = Mappersqlsession.Sqlsessions();
        CRUDMapper CM = SS.getMapper(CRUDMapper.class);
        CM.deletemany(StudentNumber);
        SS.commit();
        SS.close();
    }
    @Test
    public void test0() throws IOException {
        SqlSession SS = Mappersqlsession.Sqlsessions();
        List<User> list = new ArrayList<>();
        list =SS.selectList("Mybatis.Mapper.CRUDMapper.Selectall");
        System.out.println("---------------------------");
        System.out.println(list);
        System.out.println("---------------------------");
        SS.close();
    }
}
