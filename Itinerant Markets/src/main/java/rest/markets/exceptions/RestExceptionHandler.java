package rest.markets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * This class handles an exception's throw.
 * It returns a typical http status, which explain the kind of problem occurred,
 * and the exception handled.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

		/**
		 * This method handle all the exceptions that aren't handled 
		 * from other methods in this class.
		 * @param ex the exception thrown
		 * @return the http internal server error status and the exception
		 */
		@ExceptionHandler (value = Exception.class)
		public ResponseEntity<Object> handleAnyException(Exception ex){
			return new ResponseEntity<Object>(ex,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		/**
		 * This method handle null pointer exceptions,
		 * that occur when no content is found. 
		 * @param ex the null pointer exception thrown
		 * @return the http no content status and the exception
		 */
		@ExceptionHandler (value = NullPointerException.class)
		public ResponseEntity<Object> handleNullPointerException(NullPointerException ex){
			return new ResponseEntity<Object>(ex,HttpStatus.NO_CONTENT);
		}
		
		/**
		 * This method handle {@link ResourceNotFoundException}, that 
		 * occur when no resources match the requested parameters
		 * @param ex the exceptions thrown
		 * @return the http not found status and the exception
		 */
		@ExceptionHandler (value = ResourceNotFoundException.class)
		public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex){
			return new ResponseEntity<Object>(ex,HttpStatus.NOT_FOUND);
		}
		
		/**
		 * This method handle {@link NotExistingFieldException} and
		 * {@link NotExistingFilterException}, that occur when a wrong field or filter
		 * is sent form a user request.
		 * @param ex the exceptions thrown
		 * @return the http bad request status and the exception
		 */
		@ExceptionHandler (value = {NotExistingFieldException.class, NotExistingFilterException.class})
		public ResponseEntity<Object> handleNotExistingFieldException(NotExistingFieldException ex){
			return new ResponseEntity<Object>(ex,HttpStatus.BAD_REQUEST);
		}
		
		/**
		 * This method handle all the json related exceptions that occur
		 * when it is impossible to parse the request in a json format.
		 * @param ex the exceptions thrown
		 * @return the http internal server error status and the exception
		 */
		@ExceptionHandler (value = {JsonParseException.class,JsonMappingException.class})
		public ResponseEntity<Object> handleJsonException(Exception ex){
			return new ResponseEntity<Object>(ex,HttpStatus.BAD_REQUEST);
		}
}
