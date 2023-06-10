package Firm;

import Mybatis.Mybatis_GJL.Brand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/servlets1")
public class Servlets extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Brand> brands = new ArrayList<Brand>();
        brands.add(new Brand(1,"��ֻ����","��ֻ����",100,"��ֻ���󣬺óԲ��ϻ�",1));
        brands.add(new Brand(2,"���¿�","���¿�",200,"���¿⣬��������",0));
        brands.add(new Brand(3,"С��","С�׿Ƽ����޹�˾",1000,"Ϊ���ն���",1));

        request.setAttribute("status",1);
        request.setAttribute("brands", brands);

        System.out.println(brands);

        request.getRequestDispatcher("/dome/dome2.jsp").forward(request, response);
        //request.getRequestDispatcher("/JSP/dome1.jsp").forward(request,response);

    }
}
