package ServletDome;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/Servletdome5")
public class Servletdome5 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //��ù�������
        Object hubbies = request.getAttribute("hubbies");
        System.out.println(hubbies);
        //ʹ��Response������Ӧ����
        PrintWriter writer = response.getWriter();
        response.setHeader("content-type","text/html;charset=utf-8");
        writer.write("<h1>"+"hello "+"<h1>");
    }
}
