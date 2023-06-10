package Firm;

import Mybatis.Mapper.FirmMapper;
import Mybatis.Mybatis_GJL.Brand;
import Mybatis.Mybatis_GJL.Mappersqlsession;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/FirmServletSelect")
public class FirmServletSelect extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SqlSession SS = Mappersqlsession.Sqlsessions1();
        FirmMapper firmMapper = SS.getMapper(FirmMapper.class);
        List<Brand> brands = firmMapper.FirmSelectall();

        Object name = request.getSession().getAttribute("name");

        request.setAttribute("name", name);
        request.setAttribute("brands", brands);
        request.getRequestDispatcher("/FirmJSP/Firmselect.jsp").forward(request, response);
    }
}
