package oservices.order;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
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
}
