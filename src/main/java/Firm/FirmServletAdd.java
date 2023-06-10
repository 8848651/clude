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

@WebServlet(value = "/FirmServletAdd")
public class FirmServletAdd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String brandName = request.getParameter("brandName");
        String companyName = request.getParameter("companyName");
        int ordered = Integer.parseInt(request.getParameter("ordered"));
        String description = request.getParameter("description");
        int status = Integer.parseInt(request.getParameter("status"));

        Brand brand = new Brand(brandName,companyName,ordered,description,status);

        SqlSession SS = Mappersqlsession.Sqlsessions1();
        FirmMapper firmMapper = SS.getMapper(FirmMapper.class);
        firmMapper.insert(brand);
        SS.commit();
        SS.close();

        String reqpPath = request.getContextPath().trim();
        response.sendRedirect(reqpPath+"/FirmServletSelect");
    }
}
