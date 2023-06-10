package Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/setCookie")
public class setCookie extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String reqpName = request.getParameter("usename");
        byte[] bytes = reqpName.getBytes(StandardCharsets.ISO_8859_1);
        String reqpName1 = new String(bytes, "UTF-8");
        
        //设置cookie中文
        String name = "张三";
        name=URLEncoder.encode(name,"UTF-8");
        //发送cookie
        Cookie cookie = new Cookie("usename",name);

        //设置存活时间
        cookie.setMaxAge(60 * 60 * 3);

        response.addCookie(cookie);
    }
}
