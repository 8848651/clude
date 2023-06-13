package landing;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.Jedis;


import javax.sql.DataSource;

@EnableTransactionManagement
@SpringBootApplication
public class LandApplication {

    public static void main(String[] args) {
        SpringApplication.run(LandApplication.class, args);
    }

    @Bean
    public Jedis Jedis (){
        return  new Jedis("192.168.70.130", 6379);
    }

    @Bean
    public MessageConverter MessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager DSTM = new DataSourceTransactionManager();
        DSTM.setDataSource(dataSource);
        return DSTM;
    }

}
