package com.example.demo;

import org.springframework.boot.SpringApplication;
import com.example.config.FileStorageProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.common.showApi;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
@EnableJpaRepositories(basePackages = {"com.example"})
@EntityScan(basePackages = {"com.example"})
@EnableSwagger2
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class Demo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}
	Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select() 
                .apis(RequestHandlerSelectors.withClassAnnotation(showApi.class))
                .paths(PathSelectors.any())
                .build();
}      
	
	
	 public WebMvcConfigurer configure() {
	  return new WebMvcConfigurer() {
	   @Override
	   public void addCorsMappings(CorsRegistry reg) {
	    reg.addMapping("/**").allowedOrigins("*");
	   }
	  };
	  
	 }

}
