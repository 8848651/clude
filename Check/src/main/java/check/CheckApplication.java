package check;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class CheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckApplication.class, args);
    }

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.70.130:9200")
        ));
    }

    @Bean
    public MessageConverter MessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Jedis Jedis (){
        Jedis jedis = new Jedis("192.168.70.130", 6379);
        jedis.select(1);
        return jedis;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    //动态选择负载均衡算法
    @Bean
    public IRule RandomRule() {
        return new RandomRule();
    }


}
