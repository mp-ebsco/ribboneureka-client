package ribboneureka.client.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@RibbonClient(name = "platform.training.ribboneureka.searchservice")
@Configuration
public class RibbonConfig {
  
    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){
      return new RestTemplate();
    }
}