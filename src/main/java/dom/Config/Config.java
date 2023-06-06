package dom.Config;

import DAO.Factory.FactorJDBC;
import DAO.Factory.FactorMapperBean;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author 86151
 */
//设置IOC
@Configuration
//加载{"dom.Service","DAO","View","Section"}下所有
@ComponentScan({"dom.Service","DAO", "Affairs","Section","CS"})
//加载切面类
//AOP获取不到原Bean只能得到代理类对象
@EnableAspectJAutoProxy
//告诉Spring开了事务
@EnableTransactionManagement
//导入properties文件
@PropertySource({"classpath:Properties/Jdbc.properties"})
//导入第三方配置Bean
@Import({FactorJDBC.class, FactorMapperBean.class})
public class Config {
}
