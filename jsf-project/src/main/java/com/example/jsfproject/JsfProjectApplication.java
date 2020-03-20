package com.example.jsfproject;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sun.faces.config.ConfigureListener;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({ "com.example.jsfproject" })
public class JsfProjectApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(JsfProjectApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JsfProjectApplication.class);
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public FacesServlet facesServlet() {
	    return new FacesServlet();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletRegistrationBean facesServletRegistration() {
	    ServletRegistrationBean registration = new   ServletRegistrationBean(facesServlet(), new String[] { "*.xhtml" });
	    registration.setName("FacesServlet");
	    registration.setLoadOnStartup(1);
	    return registration;
	}

	@Configuration
	static class ConfigureJSFContextParameters implements ServletContextInitializer {

	    @Override
	    public void onStartup(ServletContext servletContext) throws ServletException {
	        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", "true");
	        servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
	        servletContext.setInitParameter("encoding", "UTF-8");
	    }
	}
	
	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
	    return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
	}
}
