package AJAX;

import Mybatis.Mybatis_GJL.Brand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebServlet(value = "/AjaxDome")
public class AjaxDome extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<String> set = new HashSet();
        set.add("209044521");
        set.add("209044522");
        set.add("209044523");
        set.add("209044524");

        String usename = request.getParameter("usename");
        String str = String.valueOf(set.contains(usename));
        System.out.println(str);

        response.getWriter().write(str);

    }
}
