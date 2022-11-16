package com.bilgeadam.BilgisayarTeknikServisi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BeanFactory implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("*.jpg", "*.png").addResourceLocations("file:///C:/Teknik/",
				"classpath:/static/images/");
		registry.addResourceHandler("*.css").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("*.js").addResourceLocations("classpath:/static/js/");
	}

}
