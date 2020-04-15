package com.webServices.SpringWebServices.soap;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema ;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;

import org.hibernate.mapping.Collection;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

//enable spring web services
// spring config file
@EnableWs
@Configuration
public class WebservicesConfiguration extends WsConfigurerAdapter {
//message dispatcher servlet 
	
	@Bean
	public ServletRegistrationBean MessageDispatcherServlet(ApplicationContext context) {
		
		MessageDispatcherServlet dispatcherServlet = new MessageDispatcherServlet() ;
		dispatcherServlet.setApplicationContext(context);
		dispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(dispatcherServlet , "/ws/*");
		
		
	}
	
	// /ws/courses.wsdl
	// course-details.xsd
	
	@Bean(name="course")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema courseSchema) {
		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
		defaultWsdl11Definition.setPortTypeName("CoursePort");
		defaultWsdl11Definition.setTargetNamespace("http:SpringWebServices.webServices.com/course");
        defaultWsdl11Definition.setLocationUri("/ws");
        defaultWsdl11Definition.setSchema(courseSchema);
        
		return defaultWsdl11Definition ;
	}
	
	
	@Bean
	public XsdSchema courseSchema() {
		return new SimpleXsdSchema(new ClassPathResource("Course-details.xsd"));
	}
	
	@Bean
	public XwsSecurityInterceptor securtyInterceptor() {
		XwsSecurityInterceptor interceptor = new  XwsSecurityInterceptor() ;
		
		
		//callback handler
		//security policy 
		interceptor.setCallbackHandler(callbackHandler());
		interceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
		return interceptor ;
		
	}
	
	@Bean
	public SimplePasswordValidationCallbackHandler callbackHandler() {
		// TODO Auto-generated method stub
		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
		handler.setUsersMap(Collections.singletonMap("user" , "password"));
		return handler;
	}

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		
		interceptors.add(securtyInterceptor());
	}

}
