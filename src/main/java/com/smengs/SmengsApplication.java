package com.smengs;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.context.event.EventListener;


@SpringBootApplication
public class SmengsApplication extends SpringBootServletInitializer{
	
	
	  @Override protected SpringApplicationBuilder
	  configure(SpringApplicationBuilder application) { return
	  application.sources(SmengsApplication.class); }
	 
	
	
	

	
	public static void main(String[] args) {
		SpringApplication.run(SmengsApplication.class, args);
		
	}


}
 