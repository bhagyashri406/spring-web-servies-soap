package com.webServices.SpringWebServices.soap;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

import ch.qos.logback.core.net.server.Client;

@SoapFault(faultCode=FaultCode.CUSTOM ,customFaultCode = "{http:SpringWebServices.webServices.com/course}001_Course_Not_Found")
public class CourseNotFountExceptions extends RuntimeException {

	

	public CourseNotFountExceptions(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	

	
	
}
