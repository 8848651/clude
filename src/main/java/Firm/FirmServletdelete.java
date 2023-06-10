package Firm;

import Mybatis.Mapper.FirmMapper;
import Mybatis.Mybatis_GJL.Mappersqlsession;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value= "/FirmServletdelete")
public class FirmServletdelete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id1 = request.getParameter("id");
        System.out.println("------------------------------------"+id1+"------------------------------------");

        int id = Integer.parseInt(request.getParameter("id"));

        SqlSession SS = Mappersqlsession.Sqlsessions1();
        FirmMapper firmMapper = SS.getMapper(FirmMapper.class);
        firmMapper.delete(id);
        SS.commit();
        SS.close();
        String reqpPath = request.getContextPath().trim();
        response.sendRedirect(reqpPath+"/FirmServletSelect");
    }
}
