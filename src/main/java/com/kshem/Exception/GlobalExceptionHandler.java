package com.kshem.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles validation errors from @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
     public Map<String, String>  handleFieldException(MethodArgumentNotValidException ex) {
		
    	Map<String, String> errors = new HashMap<>();
 	
    	ex.getBindingResult().getFieldErrors()
	    .forEach(error ->
	     {
		   errors.put(error.getField(), error.getDefaultMessage());
	     }
	     );
	
	 return errors;	
   }
    
    
  //-----------------------------------------------------------------------------------
    
         @ExceptionHandler(AppointmentNotFoundException.class)
         @ResponseStatus(HttpStatus.NOT_FOUND)
         public String handleAppointmentNotFoundException(
              AppointmentNotFoundException ex) {
          return ex.getMessage();
         }
    
      
  //----------------------------------------------------------------------------------
         
         @ExceptionHandler(Exception.class)
         @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
         public String handleGeneralException(Exception e) {

             e.printStackTrace(); // Prints actual error in STS console

             return "Something Went Wrong: " + e.getMessage();
         }
    
}

