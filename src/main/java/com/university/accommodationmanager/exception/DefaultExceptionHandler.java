package  com.university.accommodationmanager.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@EnableWebMvc
@Slf4j
public class DefaultExceptionHandler  extends ResponseEntityExceptionHandler{
	

	 @ExceptionHandler(value = NoMatchForFilterException.class)
	  public ResponseEntity<ErrorMessage> resourceNotFoundException(NoMatchForFilterException ex, HttpServletRequest  request) {
		 log.error("some error occured"+ex.getMessage());
		 ErrorMessage message = new ErrorMessage(
	        new Date(),400,"Not Found",
	        ex.getMessage(),request.getRequestURI());
	    return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	  }
	 
	 @ExceptionHandler(value = NoAccomodationAvailableException.class)
	  public ResponseEntity<ErrorMessage> resourceNotFoundException(NoAccomodationAvailableException ex, HttpServletRequest  request) {
	   
		 log.error("some error occured"+ex.getMessage());
		 ErrorMessage message = new ErrorMessage(
	        new Date(),400,"Not Found",
	        ex.getMessage(),request.getRequestURI());
	    return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	  }
	 
	 @ExceptionHandler(value = NoRoomMateAvailableException.class)
	  public ResponseEntity<ErrorMessage> resourceNotFoundException(NoRoomMateAvailableException ex, HttpServletRequest  request) {
	   
		 log.error("some error occured"+ex.getMessage());
		 ErrorMessage message = new ErrorMessage(
	        new Date(),400,"Not Found",
	        ex.getMessage(),request.getRequestURI());
	    return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	  }
	 
	 @ExceptionHandler(value = Exception.class)
	  public ResponseEntity<ErrorMessage> genericException(Exception ex, HttpServletRequest request) {
	    log.error("some error occured"+ex.getMessage());
		 ErrorMessage message = new ErrorMessage(
	        new Date(),500,"Internal Error",
	        "Exception Occured"+ex.getMessage(),request.getRequestURI());
	    return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	  }

}
