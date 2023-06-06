package DAO.Factory;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.util.impl.PlaintextPropertiesPasswordManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;


import javax.sql.DataSource;
@Repository("FactorJDBC")
public class FactorJDBC {
    @Value("${jdbc.driver}")
    private String ClassName;
    @Value("${jdbc.url}")
    private String Url;
    @Value("${jdbc.username}")
    private String Username;
    @Value("${jdbc.password}")
    private String Password;
    //管理第三方Bean
    @Bean
    public DataSource dataSource(){
        DruidDataSource DD=new DruidDataSource();
        DD.setDriverClassName(ClassName);
        DD.setUrl(Url);
        DD.setUsername(Username);
        DD.setPassword(Password);
        return DD;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager DSTM = new DataSourceTransactionManager();
        DSTM.setDataSource(dataSource);
        return DSTM;
    }
}
