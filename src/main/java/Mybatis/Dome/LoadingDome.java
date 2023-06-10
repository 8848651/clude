package Mybatis.Dome;

import Mybatis.Mapper.CRUDMapper;
import Mybatis.Mapper.UserLandingMapper;
import Mybatis.Mybatis_GJL.Mappersqlsession;
import Mybatis.Mybatis_GJL.UserLoading;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;

public class LoadingDome {
    @Test
    public void test1() throws IOException {
        String str="";
        SqlSession SS = Mappersqlsession.Sqlsessions();
        UserLandingMapper ULM = SS.getMapper(UserLandingMapper.class);
        UserLoading LPW = ULM.Loadingpassword(str);
        SS.close();
        System.out.println("-----------------------------");
        System.out.println(LPW);
    }
    @Test
    public void test2() throws IOException {
        UserLoading User=new UserLoading("209044528","209044528");
        SqlSession SS = Mappersqlsession.Sqlsessions();
        UserLandingMapper ULM = SS.getMapper(UserLandingMapper.class);
        ULM.insert(User);
        SS.commit();
        SS.close();
    }
    @Test
    public void test3() throws IOException {
        String str="209044524";
        SqlSession SS = Mappersqlsession.Sqlsessions();
        UserLandingMapper ULM = SS.getMapper(UserLandingMapper.class);
        ULM.deleteone(str);
        SS.commit();
        SS.close();
    }
    @Test
    public void test4() throws IOException {
        UserLoading User=new UserLoading("209044528","20904452#");
        SqlSession SS = Mappersqlsession.Sqlsessions();
        UserLandingMapper ULM = SS.getMapper(UserLandingMapper.class);
        int update = ULM.update(User);
        SS.commit();
        SS.close();
        System.out.println(update);
    }
    @Test
    public void test5() throws IOException {
        UserLoading User=new UserLoading("209044524","209044524");
        SqlSession SS = Mappersqlsession.Sqlsessions();
        UserLandingMapper ULM = SS.getMapper(UserLandingMapper.class);
        UserLoading loading1 = ULM.Loadingpassword(User);

        String name = "209044525".trim();
        UserLoading loading2 = ULM.Loadingpassword(name);
        SS.close();
        System.out.println(loading1);
        System.out.println(loading2);
    }
}
