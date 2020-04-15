package com.webServices.SpringWebServices.soap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.webServices.SpringWebServices.soap.CourseDetailService.Status;
import com.webservices.springwebservices.course.CourseDetails;
import com.webservices.springwebservices.course.DeleteCourseDetailRequest;
import com.webservices.springwebservices.course.DeleteCourseDetailResponse;
import com.webservices.springwebservices.course.GetAllCourseDetailsRequest;
import com.webservices.springwebservices.course.GetAllCourseDetailsResponse;
import com.webservices.springwebservices.course.GetCourseDetailsRequest;
import com.webservices.springwebservices.course.GetCourseDetailsResponse;


@Endpoint
public class CourseDetailsEndpoint {

	
	@Autowired
	CourseDetailService service ;
	
	/*
	 * methods 
	 * input- request
	 *  output - response
	 */
	@PayloadRoot(namespace ="http:SpringWebServices.webServices.com/course", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public  GetCourseDetailsResponse GetRsponse(@RequestPayload GetCourseDetailsRequest request ) {
		
		Course course= service.FindById(request.getId()) ;
		
		if(course == null)throw new CourseNotFountExceptions("invalid course Id : "+ request.getId() );
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDiscription(course.getDiscription());
		GetCourseDetailsResponse courseDetailsResponse=	new  GetCourseDetailsResponse();
		courseDetailsResponse.setCourseDetails(courseDetails);
	
		return courseDetailsResponse;
	}
	
	@PayloadRoot(namespace ="http:SpringWebServices.webServices.com/course", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public  GetAllCourseDetailsResponse GetRsponse(@RequestPayload GetAllCourseDetailsRequest request ) {
		
		List<Course> courses= service.FindAll();
		
		GetAllCourseDetailsResponse courseDetailsRespons= new GetAllCourseDetailsResponse();
		//courseDetailsResponse.setCourseDetails(courseDetails);
		for(Course course: courses) {
			CourseDetails courseDetails = new CourseDetails();
			courseDetails.setId(course.getId());
			courseDetails.setName(course.getName());
			courseDetails.setDiscription(course.getDiscription());
			courseDetailsRespons.getCourseDetails().add(courseDetails);
			}
		
	
		return courseDetailsRespons;
	}
	
	
	@PayloadRoot(namespace ="http:SpringWebServices.webServices.com/course", localPart = "DeleteCourseDetailRequest")
	@ResponsePayload
	public  DeleteCourseDetailResponse GetRsponse(@RequestPayload DeleteCourseDetailRequest request ) {
		
		Status status = service.deleteBYId(request.getId()) ;
		DeleteCourseDetailResponse courseDetailResponse = new DeleteCourseDetailResponse();
		courseDetailResponse.setStatus(mapStatus(status));
		return courseDetailResponse ;
	}

	private com.webservices.springwebservices.course.Status mapStatus(Status status) {
		// TODO Auto-generated method stub
		
		if(status == Status.failure) {
			return com.webservices.springwebservices.course.Status.FAILURE;
		}else {
			return com.webservices.springwebservices.course.Status.SUCCESS ;
		}
		
	}
}
