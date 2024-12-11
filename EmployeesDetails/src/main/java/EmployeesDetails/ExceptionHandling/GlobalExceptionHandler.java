package EmployeesDetails.ExceptionHandling;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {
	    @ExceptionHandler(HttpMessageNotReadableException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
	        Throwable cause = ex.getMostSpecificCause();
	        String message = "Invalid JSON format.";

	        if (cause != null && cause.getMessage().contains("Unexpected end-of-input")) {
	            message = "Malformed JSON: Unexpected end of input.";
	        }
	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setMessage(message);
	        errorResponse.setDetails(ex.getMessage()); 
	        return errorResponse;
}
}
