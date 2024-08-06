package com.tinqinacademy.comments.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()//scanBasePackages = "com.tinqinacademy.comments")
@ComponentScan(basePackages = "com.tinqinacademy.comments")
@EnableJpaRepositories(basePackages = "com.tinqinacademy.comments.persistence.repositories")
@EntityScan(basePackages = "com.tinqinacademy.comments.persistence.entities")
public class CommentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentsApplication.class, args);
    }

}
