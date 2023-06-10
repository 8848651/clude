package landing;

import Mybatis.Mapper.UserLandingMapper;
import Mybatis.Mybatis_GJL.Mappersqlsession;
import Mybatis.Mybatis_GJL.UserLoading;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value= "/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String reqpPath = request.getContextPath();

        String name = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        UserLoading user=new UserLoading(name,password);


        SqlSession SS = Mappersqlsession.Sqlsessions();
        UserLandingMapper ULM = SS.getMapper(UserLandingMapper.class);
        UserLoading LPW = ULM.Loadingpassword(name);


        if(!password.equals(LPW.getPassword())){
            SS.close();
            request.setAttribute("loginmistake", "密码错误");
            request.getRequestDispatcher("/landingHtml/login.jsp").forward(request, response);
        }else {
            String remember="0";
            remember = request.getParameter("remember");
            if ("1".equals(remember)) {
                Cookie cookie1 = new Cookie("usename",name);
                Cookie cookie2 = new Cookie("password",password);
                cookie1.setMaxAge(60 * 60 );
                cookie2.setMaxAge(60 * 60 );
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }

            SS.close();
            request.getSession().setAttribute("name", LPW.getName());
            response.sendRedirect(reqpPath+"/FirmServletSelect");
        }

    }
}
