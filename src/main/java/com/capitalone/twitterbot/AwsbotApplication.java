package com.capitalone.twitterbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.capitalone.twitterbot")
public class AwsbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsbotApplication.class, args);
	}
}
