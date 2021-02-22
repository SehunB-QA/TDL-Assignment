package com.qa.tdlassignmentbackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.qa.tdlassignmentbackend"})
public class TdlAssignmentBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdlAssignmentBackendApplication.class, args);
	}

}
