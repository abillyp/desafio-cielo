package br.com.cielo.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig{
	
	@Bean
	public Docket detalheApi() {
		 
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
 
		docket
		.select()
		//.apis(RequestHandlerSelectors.basePackage("br.com.cielo.desafio"))
		.apis(RequestHandlerSelectors.any())   
		.paths(PathSelectors.any())
		.build()
		.apiInfo(this.informacoesApi().build());
 
		return docket;
	}	
	

	    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/swagger-ui.html")
	                .addResourceLocations("classpath:/META-INF/resources/");
	 
	        registry.addResourceHandler("/webjars/**")
	                .addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }	
	

	    
	    
	    
	private ApiInfoBuilder informacoesApi() {
		 
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
		apiInfoBuilder.title("Desafio Cielo");
		apiInfoBuilder.description("Api para consulta de Extrado de Lançamento em conta.");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.contact(new Contact("Alessandro Billy","","abillyp@gmail.com"));
		return apiInfoBuilder;
 
	}	

}