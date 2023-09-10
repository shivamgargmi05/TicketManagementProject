package in.ineuron.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.ineuron.error.ErrorDetails;
import in.ineuron.exceptions.TouristNotFoundException;

@RestControllerAdvice	// @ControllerAdvice(@Component) + @ResponseBody - to tell RestController follow this advice to handle exception, if any exception raised propagated from service to controller, don't go with default exception handler BasicErrorController errorHtml() & error()
public class TouristErrorControllerAdvice {

	// handles only TouristNotFoundException raised
	// @ExceptionHandler - If any exception related to RestController logic raised, so to handle it and send in response body using Response Entity.
	@ExceptionHandler(TouristNotFoundException.class)	// tell exception object class name
	public ResponseEntity<ErrorDetails> handleNotFoundException(TouristNotFoundException e) {	// get exception object
		System.out.println("TouristErrorControllerAdvice handleNotFoundException() is called");
	
		ErrorDetails body=new ErrorDetails(LocalDateTime.now(), e.getMessage(), "404-Not Found");
		
		return new ResponseEntity<ErrorDetails>(body, HttpStatus.NOT_FOUND);
		// response will be sent by server as java object but will be received by client as json object automatically conversion DispatcherServlet HttpMessageConverter
	}
	
	// Global Exception Handler - handles all type of exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAllOtherExceptions(Exception e) {
		System.out.println("TouristErrorControllerAdvice handleAllOtherExceptions() is called");
		
		ErrorDetails body=new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Problem Occured in execution");
		
		return new ResponseEntity<ErrorDetails>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		// response will be sent by server as java object but will be received by client as json object automatically conversion DispatcherServlet HttpMessageConverter
	}
	
}
