package com.devops.endpoint;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EndpointApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(EndpointApplication.class, args);
		 String[] beanNames = context.getBeanDefinitionNames();
		    for (String beanName : beanNames) {
		      System.out.println(beanName);
		    }
	}

}
