package com.pluralsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SakilaSpringBootApplication {

	public static void main(String[] args) {
		// SpringApplication.run(SakilaSpringBootApplication.class, args);

		ApplicationContext context = SpringApplication.run(SakilaSpringBootApplication.class, args);
		var beans = context.getBeanDefinitionNames();
		for (String beanName : beans) {
			System.out.println(beanName);
		}
	}

}
