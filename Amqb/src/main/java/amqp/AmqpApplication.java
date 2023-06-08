package amqp;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AmqpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqpApplication.class, args);
	}

	@Bean
	public MessageConverter MessageConverter(){
		return new Jackson2JsonMessageConverter();
	}

}
