package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebFilter("/*")
public class FilterDome implements Filter {
    @Override
    public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest Requests= (HttpServletRequest) Request;
        HttpServletResponse Responses = (HttpServletResponse) Response;
        String url = Requests.getRequestURI();
        String reqpPath = Requests.getContextPath();
        System.out.println("URL:"+url);
        //登陆注册相关资源放行
        String[] url1= {"/landingHtml/","/Dome1/","/Dome2/","/JS/","/FirmJSP2/"};
        //登陆的服务器资源，注册的服务器资源，验证码的服务器资源
        String[] url2={"/loginServlet","/RegistServlet","/CapthcaServlet","/AjaxDome","/AxiosDome"};
        String[] url3=new String[url1.length+url2.length];
        System.arraycopy(url1, 0, url3, 0, url1.length);
        System.arraycopy(url2, 0, url3, url1.length, url2.length);

        for (String s:url3) {
            if(url.contains(s)){
                System.out.println("放行1");
                filterChain.doFilter(Request, Response);
                return;
            }
        }


        HttpSession session = Requests.getSession();
        String name = (String) session.getAttribute("name");

        if (name != null) {
            // 放行
            System.out.println("放行2");
            filterChain.doFilter(Request, Response);

        }else {
            System.out.println("重定向1");
            Responses.sendRedirect(reqpPath+"/landingHtml/login.jsp");
        }


    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {

    }
}
