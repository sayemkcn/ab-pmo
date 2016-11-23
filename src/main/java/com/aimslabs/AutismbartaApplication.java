package com.aimslabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EntityScan
public class AutismbartaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutismbartaApplication.class, args);
    }
}
