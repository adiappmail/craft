package com.intuit.craft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.intuit.craft.*"})
@Configuration
@EntityScan(basePackages = {"com.intuit.craft.*"})  // scan JPA entities
public class CraftApplication {

    public static void main(String[] args) {
        SpringApplication.run(CraftApplication.class, args);
    }

}
