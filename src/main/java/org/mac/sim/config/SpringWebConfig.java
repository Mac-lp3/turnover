package org.mac.sim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan({ "org.mac.sim.controller" })
public class SpringWebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/js/**").addResourceLocations("/app/js/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/app/fonts/");
		registry.addResourceHandler("/css/**").addResourceLocations("/app/css/");
		registry.addResourceHandler("/media/**").addResourceLocations("/app/media/");
		registry.addResourceHandler("/app/**").addResourceLocations("/app/");
		registry.addResourceHandler("index.html").addResourceLocations("/index.html");
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// viewResolver.setViewClass(JstlView.class);
		// viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

}