package com.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(
	  info=@Info(
			  title="Spring Boot Rest Api Documentation",
			  description="Spring Boot Rest Api Documentation",
			  version="v1.0",
			  contact=@Contact(
					    name="Jitendra",
					    email="jitu@gamil.com"
					  )
			  )	
	)
public class RestApiUserExApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiUserExApplication.class, args);
	}

}
