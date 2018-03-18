package com.auth0.samples;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"com.auth0.samples"})
public class SpringAppConfig implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) {
		
		AnnotationConfigWebApplicationContext rootContext= new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringAppConfig.class);
		
		container.addListener(new ContextLoaderListener(rootContext));
		
		AnnotationConfigWebApplicationContext dispatcherContext= new AnnotationConfigWebApplicationContext();
		
		ServletRegistration.Dynamic dispatcher= container.addServlet("dispatcher",new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
	}
}
