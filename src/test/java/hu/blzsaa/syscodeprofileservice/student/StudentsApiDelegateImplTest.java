package hu.blzsaa.syscodeprofileservice.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import hu.blzsaa.syscodeprofileservice.model.Student;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class StudentsApiDelegateImplTest {

	@Mock
	private StudentService studentService;

	private StudentsApiDelegateImpl underTest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		underTest = new StudentsApiDelegateImpl(studentService);
	}

	@Test
	void getStudentByIdShouldDelegateToTheService() {
		// given
		UUID uuid = UUID.randomUUID();
		Student student = new Student(uuid, "name", "emailAddress");
		doReturn(student).when(studentService).getStudentById(uuid);

		// when
		var actual = underTest.getStudentById(uuid);

		// then
		assertThat(actual).isEqualTo(ResponseEntity.ok(student));
	}

}
