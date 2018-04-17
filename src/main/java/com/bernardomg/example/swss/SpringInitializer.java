package com.bernardomg.example.swss;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

public class SpringInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {

		// Create the 'web' Spring application context
		AnnotationConfigWebApplicationContext rootWebContext = new AnnotationConfigWebApplicationContext();
		//classpath:/context/web-service.xml
		// rootContext.register(AppConfig.class);
		// Manage the lifecycle of the root application context
		servletContext.addListener(new ContextLoaderListener(rootWebContext));

		buildServlet(servletContext, null, "unsecure-ws", "/unsecure");//classpath:/context/servlet/servlet-unsecure.xml
		buildServlet(servletContext, null, "password-plain-xwss-ws", "/password/plain/xwss");//classpath:/context/servlet/password/plain/xwss/servlet-password-plain-xwss.xml
		buildServlet(servletContext, null, "password-digest-xwss-ws", "/password/digest/xwss");//classpath:/context/servlet/password/digest/xwss/servlet-password-digest-xwss.xml
		
		
	}

	private void buildServlet(ServletContext servletContext, Class<?> configClass, String servletName, String mapping) {
		AnnotationConfigWebApplicationContext ctxWs = new AnnotationConfigWebApplicationContext();
		if (configClass != null) {
			ctxWs.register(configClass);			
		}
		ctxWs.setServletContext(servletContext);
		ServletRegistration.Dynamic servletWs = servletContext.addServlet(servletName, new MessageDispatcherServlet(ctxWs));
		servletWs.setLoadOnStartup(1);
		servletWs.addMapping(mapping);
		servletWs.setInitParameter("transformWsdlLocations", "true");
	}

}