
package tk.nikhil.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer{

	@Bean
	public Docket docket() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiEndPointsInfo()).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
		return docket;
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("login").description("Login APIs")
				.contact(new Contact("Nikhil", "https://Nikhil.tk/", " nikhil@ultimate-in.com")).version("1.0.0")
				.build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (true) {
			registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/");
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
	}

}
