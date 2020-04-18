package com.cg.go.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(scanBasePackages = {"com.cg.go"})
@ComponentScan(basePackages = {"com.cg.go"})
public class GreatoutdoorProductManagementSpring1Application {

	public static void main(String[] args) {
		SpringApplication.run(GreatoutdoorProductManagementSpring1Application.class, args);
	}

}
