package com.dxb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityOauth2Application {
    public static void main(String args[]){
        new SpringApplication(SpringSecurityOauth2Application.class).run(args);
    }

}
