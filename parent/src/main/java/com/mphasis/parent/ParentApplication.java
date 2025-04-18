package com.mphasis.parent;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 
 
 
@SpringBootApplication
@ComponentScan(basePackages= "com.mphasis.parent")
@EnableJpaRepositories(basePackages = "com.mphasis.parent")
public class ParentApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(ParentApplication.class, args);
	}
 
}