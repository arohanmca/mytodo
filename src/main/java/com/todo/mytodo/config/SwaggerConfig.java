package com.todo.mytodo.config;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("ROHAN ANAND", "http://www.rohananand.com",
			"AROHAN.MCA@GMAIL.COM");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Rohan Api Documentation", "Rohan Api Documentation",
			"1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
			new ArrayList<>());
	private static final Set<String> PRODUCES_CONSUMES_AND = new HashSet<String>(
			Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.consumes(PRODUCES_CONSUMES_AND)
				.produces(PRODUCES_CONSUMES_AND);
	}

}
