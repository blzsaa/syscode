package hu.blzsaa.profileservice.advice;

import hu.blzsaa.profileservice.student.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	ProblemDetail handleStudentNotFoundException(StudentNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Student Not Found");
		return problemDetail;
	}

	@ExceptionHandler(HttpClientErrorException.class)
	ProblemDetail handleHttpClientErrorException(HttpClientErrorException e) {
		return ProblemDetail.forStatusAndDetail(e.getStatusCode(), e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	ProblemDetail handleException(Exception e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
		problemDetail.setTitle("Service Unavailable");
		return problemDetail;
	}

}
