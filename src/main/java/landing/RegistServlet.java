package landing;

import Mybatis.Mapper.UserLandingMapper;
import Mybatis.Mybatis_GJL.Mappersqlsession;
import Mybatis.Mybatis_GJL.UserLoading;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(value= "/RegistServlet")
public class RegistServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqpPath = request.getContextPath();
        HttpSession session = request.getSession();

        String name = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String captcha = request.getParameter("Captcha").trim();
        UserLoading user=new UserLoading(name,password);

        SqlSession SS = Mappersqlsession.Sqlsessions();
        UserLandingMapper ULM = SS.getMapper(UserLandingMapper.class);
        UserLoading loading = ULM.Loadingpassword(user);

        String captcha1 = (String) session.getAttribute("Capthca");
        System.out.println(captcha+" "+captcha1);

        if (!captcha.equals(captcha1)) {
            SS.close();
            request.setAttribute("Captchamistake", "验证码错误");
            request.getRequestDispatcher("/landingHtml/register.jsp").forward(request, response);
            return;
        }

        if (loading != null) {
            SS.close();
            request.setAttribute("registmistake", "用户名已存在");
            request.getRequestDispatcher("/landingHtml/register.jsp").forward(request, response);
        } else {
            ULM.insert(user);
            SS.commit();
            SS.close();
            session.invalidate();
            Cookie cookie1 = new Cookie("usename",name);
            Cookie cookie2 = new Cookie("password",password);
            cookie1.setMaxAge(60 * 60 );
            cookie2.setMaxAge(60 * 60 );
            response.addCookie(cookie1);
            response.addCookie(cookie2);

            response.sendRedirect(reqpPath+"/landingHtml/login.jsp");
        }
    }
}
