package onlinefood.exception;

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
	
	@ExceptionHandler(NoSuchFoodItemException.class)
	public ResponseEntity<String> handleNoSuchFoodItemException(NoSuchFoodItemException noSuchFoodException){
		logger.error(noSuchFoodException.getErrorCode()+"\t"+noSuchFoodException.getErrorMessage());
		return new ResponseEntity<String>("NoSuchFoodItemException : "+noSuchFoodException.getErrorCode()+"\\"+noSuchFoodException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchFoodTypeException.class)
	public ResponseEntity<String> handleNoSuchFoodTypeException(NoSuchFoodTypeException noSuchFoodException){
		logger.error(noSuchFoodException.getErrorCode()+"\t"+noSuchFoodException.getErrorMessage());
		return new ResponseEntity<String>("NoSuchFoodTypeException : "+noSuchFoodException.getErrorCode()+"\\"+noSuchFoodException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchUserException.class)
	public ResponseEntity<String> handleNoSuchUserException(NoSuchUserException noSuchUserException){
		logger.error(noSuchUserException.getErrorCode()+"\t"+noSuchUserException.getErrorMessage());
		return new ResponseEntity<String>("NoSuchUserException : "+noSuchUserException.getErrorCode()+"\\"+noSuchUserException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchAdminException.class)
	public ResponseEntity<String> handleNoSuchAdminException(NoSuchAdminException noSuchAdminException){
		logger.error(noSuchAdminException.getErrorCode()+"\t"+noSuchAdminException.getErrorMessage());
		return new ResponseEntity<String>("NoSuchAdminException : "+noSuchAdminException.getErrorCode()+"\\"+noSuchAdminException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(WrongPasswordException.class)
	public ResponseEntity<String> handleWrongPasswordException(WrongPasswordException wrongPasswordException){
		logger.error(wrongPasswordException.getErrorCode()+"\t"+wrongPasswordException.getErrorMessage());
		return new ResponseEntity<String>("WrongPasswordException : "+wrongPasswordException.getErrorCode()+"\\"+wrongPasswordException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyListException.class)
	public ResponseEntity<String> handleEmptyListException(EmptyListException emptyListException){
		logger.error(emptyListException.getErrorCode()+"\t"+emptyListException.getErrorMessage());
		return new ResponseEntity<String>("EmptyListException : "+emptyListException.getErrorCode()+"\\"+emptyListException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchOrderIdException.class)
	public ResponseEntity<String> handleNoSuchOrderIdException(NoSuchOrderIdException noSuchOrderIdException){
		logger.error(noSuchOrderIdException.getErrorCode()+"\t"+noSuchOrderIdException.getErrorMessage());
		return new ResponseEntity<String>("NoSuchOrderIdException : "+noSuchOrderIdException.getErrorCode()+"\\"+noSuchOrderIdException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
}
