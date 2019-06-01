package rest.markets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

		@ExceptionHandler (value = Exception.class)
		public ResponseEntity<Object> handleAnyException(Exception ex){
			return new ResponseEntity<Object>(ex,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@ExceptionHandler (value = NullPointerException.class)
		public ResponseEntity<Object> handleNullPointerException(NullPointerException ex){
			return new ResponseEntity<Object>(ex,HttpStatus.NO_CONTENT);
		}
		
		@ExceptionHandler (value = ResourceNotFoundException.class)
		public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex){
			return new ResponseEntity<Object>(ex,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler (value = NotExistingFieldException.class)
		public ResponseEntity<Object> handleNotExistingFieldException(NotExistingFieldException ex){
			return new ResponseEntity<Object>(ex,HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler (value = {JsonParseException.class,JsonMappingException.class})
		public ResponseEntity<Object> handleJsonException(Exception ex){
			return new ResponseEntity<Object>(ex,HttpStatus.BAD_REQUEST);
		}
}
