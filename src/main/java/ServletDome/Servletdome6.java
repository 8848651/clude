package ServletDome;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(value = "/Servletdome6")
public class Servletdome6 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = new File("D:\\Java_work\\Javaweb\\src\\main\\webapp\\resources\\ababc.png");
        //�������뵽������
        FileInputStream fis = new FileInputStream(file);
        //������д���������
        ServletOutputStream sos = response.getOutputStream();
        IOUtils.copy(fis, sos);
        fis.close();
        sos.close();
    }
}
