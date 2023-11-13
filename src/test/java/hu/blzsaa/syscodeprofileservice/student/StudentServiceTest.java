package hu.blzsaa.syscodeprofileservice.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import hu.blzsaa.syscodeprofileservice.model.Student;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class StudentServiceTest {

	@Mock
	private StudentRepository repository;

	@Mock
	private StudentMapper studentMapper;

	private StudentService underTest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		underTest = new StudentService(repository, studentMapper);
	}

	@Test
	void getStudentByIdShouldCallRepositoryAndMapTheResultToStudent() {
		// given
		UUID uuid = UUID.randomUUID();
		Student student = new Student(uuid, "name", "emailAddress");
		StudentEntity studentEntity = new StudentEntity(uuid, "name", "emailAddress");
		doReturn(student).when(studentMapper).map(studentEntity);
		doReturn(Optional.of(studentEntity)).when(repository).findById(uuid);

		// when
		var actual = underTest.getStudentById(uuid);

		// then
		assertThat(actual).isEqualTo(student);
	}

}
