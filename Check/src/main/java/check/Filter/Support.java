package check.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class Support extends WebMvcConfigurationSupport {

    @Autowired
    private Interceptor Interceptor;
    //拦截器指定拦那些
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(Interceptor).addPathPatterns("/Check", "/Check/*");
    }
    //过滤器
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Html/**").addResourceLocations("/Html/");
    }
}
