package landing;

import Mybatis.Mapper.UserLandingMapper;
import Mybatis.Mybatis_GJL.CheckCodeUtil;
import Mybatis.Mybatis_GJL.Mappersqlsession;
import Mybatis.Mybatis_GJL.UserLoading;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(value= "/CapthcaServlet")
public class CapthcaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ServletOutputStream SOS=response.getOutputStream();
        String Capthca= CheckCodeUtil.outputVerifyImage(100,50,SOS,4);
        session.setAttribute("Capthca", Capthca);
    }
}
