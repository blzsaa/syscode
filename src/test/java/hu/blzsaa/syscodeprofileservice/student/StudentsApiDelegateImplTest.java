package hu.blzsaa.syscodeprofileservice.student;

import static hu.blzsaa.syscodeprofileservice.student.TestUtils.STUDENT;
import static hu.blzsaa.syscodeprofileservice.student.TestUtils.STUDENT_CREATE_DTO;
import static hu.blzsaa.syscodeprofileservice.student.TestUtils.UUID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import hu.blzsaa.syscodeprofileservice.model.Student;
import hu.blzsaa.syscodeprofileservice.model.StudentCreateDto;
import java.net.URI;
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
		doReturn(STUDENT).when(studentService).getStudentById(UUID_1);

		// when
		var actual = underTest.getStudentById(UUID_1);

		// then
		assertThat(actual).isEqualTo(ResponseEntity.ok(STUDENT));
	}

	@Test
	void createStudentByIdShouldDelegateToTheServiceAndWrapUuidOfCreatedStudentInLocationHeader() {
		// given
		doReturn(STUDENT).when(studentService).createStudent(STUDENT_CREATE_DTO);

		// when
		var actual = underTest.createStudent(STUDENT_CREATE_DTO);

		// then
		assertThat(actual).isEqualTo(ResponseEntity.created(URI.create("/students/" + UUID_1)).build());
	}

}
