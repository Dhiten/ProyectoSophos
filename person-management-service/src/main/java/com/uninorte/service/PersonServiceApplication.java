package com.uninorte.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PersonServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PersonServiceApplication.class, args);
		TriggerManager triggers = context.getBean(TriggerManager.class);
		triggers.createTriggers();
	}

}
