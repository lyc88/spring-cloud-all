package com.ssp;

import com.ssp.health.MyHealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@SpringBootApplication
public class SpringCloudConfigClientApplication {

    private final ContextRefresher contextRefresher;
    private final Environment environment;

    @Autowired
    public SpringCloudConfigClientApplication(ContextRefresher contextRefresher, Environment environment) {this.contextRefresher = contextRefresher;
        this.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigClientApplication.class, args);
    }


//    @Scheduled(fixedRate = 5 * 1000, initialDelay = 1000)
    public void autoRefresh() {
        Set<String> refresh = contextRefresher.refresh();
        refresh.forEach(s ->
                System.err.printf("[Thread :%s] 当前配置已更新，key=%s，value=%s \n",
                        Thread.currentThread().getName(),
                        s,
                        environment.getProperty(s)));
    }

    @Bean
    public MyHealthIndicator myHealthIndicator(){
        return new MyHealthIndicator();
    }
}
