
package com.ultimate.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer{

	@Bean
	public Docket docket() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
				.apiInfo(apiEndPointsInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
		return docket;
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("UltimatePosUser").description("UltimatePosUserApplication APIs")
				.contact(new Contact("Ultimate", "https://ultimatetek.in/", " idris@ultimate-in.com")).version("1.0.0")
				.build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (true) {
			registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/");
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
	}
	
	  private ApiKey apiKey() {
		    return new ApiKey("JWT", "Authorization", "header");
		  }
	  
	  private SecurityContext securityContext() {
		    return SecurityContext.builder()
		        .securityReferences(defaultAuth())
		        .build();
		  }
	  
	  List<SecurityReference> defaultAuth() {
		    AuthorizationScope authorizationScope
		        = new AuthorizationScope("global", "accessEverything");
		    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		    authorizationScopes[0] = authorizationScope;
		    return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
		  }
}
