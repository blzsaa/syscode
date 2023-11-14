package hu.blzsaa.syscodeprofileservice.student;

import static hu.blzsaa.syscodeprofileservice.student.TestUtils.STUDENT;
import static hu.blzsaa.syscodeprofileservice.student.TestUtils.STUDENT_2;
import static hu.blzsaa.syscodeprofileservice.student.TestUtils.STUDENT_CREATE_DTO;
import static hu.blzsaa.syscodeprofileservice.student.TestUtils.STUDENT_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.net.URI;
import java.util.List;
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
		doReturn(STUDENT).when(studentService).getStudentById(STUDENT_ID);

		// when
		var actual = underTest.getStudentById(STUDENT_ID);

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
		assertThat(actual).isEqualTo(ResponseEntity.created(URI.create("/students/" + STUDENT_ID)).build());
	}

	@Test
	void deleteStudentShouldDelegateToTheService() {
		// when
		var actual = underTest.deleteStudent(STUDENT_ID);

		// then
		assertThat(actual).isEqualTo(ResponseEntity.noContent().build());
		verify(studentService).deleteStudent(STUDENT_ID);
	}

	@Test
	void listStudentsShouldDelegateToTheService() {
		// given
		doReturn(List.of(STUDENT, STUDENT_2)).when(studentService).listStudents();

		// when
		var actual = underTest.listStudents();

		// then
		assertThat(actual).isEqualTo(ResponseEntity.ok(List.of(STUDENT, STUDENT_2)));
	}

}
