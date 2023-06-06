package DAO.Factory;


import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


@Repository
public class FactorMapperBean {

    @Bean
    public SqlSessionFactoryBean sqlsessions(DataSource dataSource) {
        SqlSessionFactoryBean bean =new SqlSessionFactoryBean();
        bean .setTypeAliasesPackage("DAO.Implement");
        bean .setDataSource(dataSource);
        return bean ;
    }
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("DAO.Interface");
        return msc;
    }
}