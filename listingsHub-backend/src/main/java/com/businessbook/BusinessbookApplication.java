package com.businessbook;

import static com.businessbook.domain.common.WebServicesConstants.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.businessbook.platform.logging.LoggingHttpRequestInterceptor;
import com.businessbook.platform.security.BasicAuthenticationHttpInterceptor;

//TODO replace deprecated WebMvcConfigurerAdapter
@SpringBootApplication
public class BusinessbookApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(BusinessbookApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {		
		registry.addInterceptor(new LoggingHttpRequestInterceptor());
		registry.addInterceptor(new BasicAuthenticationHttpInterceptor());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods(HttpMethod.OPTIONS.name(),
												  HttpMethod.GET.name(),
												  HttpMethod.PUT.name(),
												  HttpMethod.DELETE.name(),
												  HttpMethod.POST.name())
								 .allowedOrigins("http://localhost:4200")
								 .allowCredentials(true);
	}	
	
}
