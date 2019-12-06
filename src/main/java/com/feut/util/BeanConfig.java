package com.feut.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanConfig {
	public BeanConfig() {
		
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
