package onlinefood.exception;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException){
		logger.error(emptyInputException.getErrorCode()+"\t"+emptyInputException.getErrorMessage());
		return new ResponseEntity<String>("EmptyInputException : "+emptyInputException.getErrorCode()+"\\"+emptyInputException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException elementException){
		return new ResponseEntity<String>("Requested user doesn't exist : ",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NoSuchFoodItemException.class)
	public ResponseEntity<String> handleNoSuchFoodItemException(NoSuchFoodItemException noSuchFoodException){
		logger.error(noSuchFoodException.getErrorCode()+"\t"+noSuchFoodException.getErrorMessage());
		return new ResponseEntity<String>("NoSuchFoodItemException : "+noSuchFoodException.getErrorCode()+"\\"+noSuchFoodException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchUserException.class)
	public ResponseEntity<String> handleNoSuchUserException(NoSuchUserException noSuchUserException){
		logger.error(noSuchUserException.getErrorCode()+"\t"+noSuchUserException.getErrorMessage());
		return new ResponseEntity<String>("NoSuchUserException : "+noSuchUserException.getErrorCode()+"\\"+noSuchUserException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(WrongPasswordException.class)
	public ResponseEntity<String> handleWrongPasswordException(WrongPasswordException wrongPasswordException){
		logger.error(wrongPasswordException.getErrorCode()+"\t"+wrongPasswordException.getErrorMessage());
		return new ResponseEntity<String>("WrongPasswordException : "+wrongPasswordException.getErrorCode()+"\\"+wrongPasswordException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
}
