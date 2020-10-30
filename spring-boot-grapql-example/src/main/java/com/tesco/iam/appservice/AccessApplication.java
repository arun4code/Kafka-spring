package com.tesco.iam.appservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableKafka
//@EnableAutoConfiguration
public class AccessApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccessApplication.class, args);
	}
}
