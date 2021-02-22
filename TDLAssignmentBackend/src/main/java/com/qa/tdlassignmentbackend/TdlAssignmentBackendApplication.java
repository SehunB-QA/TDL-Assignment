package com.qa.tdlassignmentbackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.qa.tdlassignmentbackend"})
public class TdlAssignmentBackendApplication  extends SpringBootServletInitializer {
	
	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(TdlAssignmentBackendApplication.class);
	    }


	public static void main(String[] args) {
		SpringApplication.run(TdlAssignmentBackendApplication.class, args);
	}

}
