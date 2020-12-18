package com.appsdeveloper.app.ws;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	Contact contact = new Contact(
		"Contact",
		"http://localhost:8088",
		"dev@dev.com"	
	);


	ApiInfo apiInfo =  new ApiInfoBuilder()
	        .title("Spring Boot REST API")
	        .description("Spring Boot REST API for greeting people")
	        .version("1.0.0")
	        .license("Apache License Version 2.0")
	        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
	        .contact(contact)
	        .build();
			
	@Bean
	public Docket apiDocket() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.appsdelevloper.app.ws"))
				.paths(PathSelectors.any())
				.build();
		
		return docket;
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public LinkDiscoverers discovers() {
		List<LinkDiscoverer> plugins = new ArrayList<>();
		plugins.add(new CollectionJsonLinkDiscoverer());
		return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
		 
	}
}