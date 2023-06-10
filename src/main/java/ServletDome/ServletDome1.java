package ServletDome;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//��ȷƥ��������Ŀ¼ƥ��
@WebServlet(urlPatterns ={"/ServletDome0", "/ServletDome2"})
public class ServletDome1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //��ȡ��������
        String reqpName = req.getParameter("usename");
        byte[] bytes = reqpName.getBytes(StandardCharsets.ISO_8859_1);
        String reqpName1 = new String(bytes, "UTF-8");
        System.out.println(reqpName1);
        //��̬��ȡ����Ŀ¼
        String reqpPath = req.getContextPath();
        System.out.println(reqpPath);
        //��ȡ�������
        String reqpValue = req.getQueryString();
        System.out.println(reqpValue);


        //ʹ��Response������Ӧ����
        resp.setHeader("content-type","text/html;charset=utf-8");
        resp.getWriter().write("<h1>"+"hello "+reqpName+"<h1>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader bR =req.getReader();
        String line = bR .readLine();
        System.out.println(line);
    }
}


