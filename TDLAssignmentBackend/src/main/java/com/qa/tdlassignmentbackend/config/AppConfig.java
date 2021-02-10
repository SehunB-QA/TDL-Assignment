package com.qa.tdlassignmentbackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class AppConfig {

	@Bean
	@Scope("prototype")
	public ModelMapper mapper() 
	{
		return new ModelMapper();
		
	}
}
