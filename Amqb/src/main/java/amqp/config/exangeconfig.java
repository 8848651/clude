package amqp.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class exangeconfig {
    //声明一个交换机Fanout
    @Bean
    public  FanoutExchange FanoutExchange(){
        return new FanoutExchange("Fanout-exange");
    }
    //声明队列1
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanoutQueue1");
    }
    //将队列一与交换机绑定
    @Bean
    public Binding bindingFanoutExchange1(Queue fanoutQueue1, FanoutExchange FanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(FanoutExchange);
    }
    //声明队列2
    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanoutQueue2");
    }
    //将队列2与交换机绑定
    @Bean
    public Binding bindingFanoutExchange2(Queue fanoutQueue2, FanoutExchange FanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(FanoutExchange);
    }
}
