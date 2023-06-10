package ServletDome;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

//��ȷƥ��������Ŀ¼ƥ��
@WebServlet(urlPatterns= "/ServletDome")
public class ServletDome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> map = req.getParameterMap();
        for (String key: map.keySet()) {
            System.out.print(key+":");
            System.out.print(Arrays.toString(map.get(key)));
            System.out.println();
        }
        String[] hubbies = req.getParameterValues("hubby");
        String usename = req.getParameter("usename");

        //��תǰ�������
        req.setAttribute("hubbies", "hubbies");
        //��Դ��ת��Servletdomes
        req.getRequestDispatcher("/Servletdome5").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        this.doGet(req, resp);
    }
}


