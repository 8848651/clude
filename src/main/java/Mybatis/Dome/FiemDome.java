package Mybatis.Dome;

import Mybatis.Mapper.FirmMapper;
import Mybatis.Mapper.UserLandingMapper;
import Mybatis.Mybatis_GJL.Brand;
import Mybatis.Mybatis_GJL.Mappersqlsession;
import Mybatis.Mybatis_GJL.UserLoading;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FiemDome {
    @Test
    public void test1() throws IOException {
        List<Brand> list = new ArrayList<>();
        SqlSession SS = Mappersqlsession.Sqlsessions1();
        FirmMapper firmMapper = SS.getMapper(FirmMapper.class);
        List<Brand> brands = firmMapper.FirmSelectall();
        System.out.println(brands);
    }
    @Test
    public void test2() throws IOException {
        Brand brand3 = new Brand(3,"小米", "小米有限公司", 50, "小米为发烧而生", 1);
        Brand brand1 = new Brand("三只松鼠", "三只松鼠", 100, "三只松鼠，好吃不上火", 1);
        Brand brand2 = new Brand("优衣库", "优衣库", 10, "优衣库，服适人生", 0);

        SqlSession SS = Mappersqlsession.Sqlsessions1();
        FirmMapper firmMapper = SS.getMapper(FirmMapper.class);
        int insert = firmMapper.insert(brand2);
        System.out.println(insert);
        SS.commit();
        SS.close();
    }
    @Test
    public void test3() throws IOException {
        Integer id=10;
        SqlSession SS = Mappersqlsession.Sqlsessions1();
        FirmMapper firmMapper = SS.getMapper(FirmMapper.class);
        firmMapper.delete(id);
        SS.commit();
        SS.close();
    }
}
