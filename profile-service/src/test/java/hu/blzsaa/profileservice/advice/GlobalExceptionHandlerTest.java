package hu.blzsaa.profileservice.advice;

import static org.assertj.core.api.Assertions.assertThat;

import hu.blzsaa.profileservice.student.StudentNotFoundException;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;

class GlobalExceptionHandlerTest {

	private GlobalExceptionHandler underTest;

	@BeforeEach
	void setUp() {
		underTest = new GlobalExceptionHandler();
	}

	@Test
	void handleStudentNotFoundExceptionShouldReturnWithCustomPorblem() {
		// given
		UUID uuid = UUID.fromString("7bee96c2-5ed3-4a7a-b166-6c4fe9659c98");
		StudentNotFoundException e = new StudentNotFoundException(uuid);

		// when
		var actual = underTest.handleStudentNotFoundException(e);

		// then
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
				"student cannot be found with id: 7bee96c2-5ed3-4a7a-b166-6c4fe9659c98");
		problemDetail.setTitle("Student Not Found");
		assertThat(actual).isEqualTo(problemDetail);
	}

	@Test
	void handleHttpClientErrorExceptionShouldReturnWithCustomProblem() {
		// given
		HttpClientErrorException e = new HttpClientErrorException(HttpStatus.NOT_FOUND);

		// when
		var actual = underTest.handleHttpClientErrorException(e);

		// then
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "404 NOT_FOUND");
		assertThat(actual).isEqualTo(problemDetail);
	}

	@Test
	void handleHExceptionShouldReturnWithCustomProblem() {
		// given
		Exception e = new Exception("exception");

		// when
		var actual = underTest.handleException(e);

		// then
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.SERVICE_UNAVAILABLE, "exception");
		problemDetail.setTitle("Service Unavailable");
		assertThat(actual).isEqualTo(problemDetail);
	}

}
