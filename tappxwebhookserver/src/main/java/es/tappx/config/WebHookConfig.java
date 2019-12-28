package es.tappx.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

@Configuration
public class WebHookConfig {
	
	@Bean(name = "app-objectMapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new Jdk8Module());
    }	
	
	
	 @Bean(name = "threadPoolTaskExecutor")
	 public Executor threadPoolTaskExecutor() {
	        return new ThreadPoolTaskExecutor();
	}

}
