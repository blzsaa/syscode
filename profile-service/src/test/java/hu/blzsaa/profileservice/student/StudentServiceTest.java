package hu.blzsaa.profileservice.student;

import static hu.blzsaa.profileservice.student.TestUtils.STUDENT;
import static hu.blzsaa.profileservice.student.TestUtils.STUDENT_2;
import static hu.blzsaa.profileservice.student.TestUtils.STUDENT_CREATE_DTO;
import static hu.blzsaa.profileservice.student.TestUtils.STUDENT_ENTITY;
import static hu.blzsaa.profileservice.student.TestUtils.STUDENT_ENTITY_2;
import static hu.blzsaa.profileservice.student.TestUtils.STUDENT_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
		doReturn(STUDENT).when(studentMapper).mapEntityToDto(STUDENT_ENTITY);
		doReturn(Optional.of(STUDENT_ENTITY)).when(repository).findById(STUDENT_ID);

		// when
		var actual = underTest.getStudentById(STUDENT_ID);

		// then
		assertThat(actual).isEqualTo(STUDENT);
	}

	@Test
	void getStudentByIdShouldThrowStudentNotFoundExceptionWhenThereIsNoStudentForUuid() {
		// given
		doReturn(Optional.empty()).when(repository).findById(STUDENT_ID);

		// when
		var actual = catchThrowable(() -> underTest.getStudentById(STUDENT_ID));

		// then
		assertThat(actual).isInstanceOf(StudentNotFoundException.class)
			.hasMessage("student cannot be found with id: " + STUDENT_ID);
	}

	@Test
	void createStudentShouldSaveToDbAndReturnWithSavedValueAsDto() {
		// given
		doReturn(STUDENT_ENTITY).when(studentMapper).mapCreateDtoToEntity(STUDENT_CREATE_DTO);
		doReturn(STUDENT_ENTITY).when(repository).save(STUDENT_ENTITY);
		doReturn(STUDENT).when(studentMapper).mapEntityToDto(STUDENT_ENTITY);

		// when
		var actual = underTest.createStudent(STUDENT_CREATE_DTO);

		// then
		assertThat(actual).isEqualTo(STUDENT);
		verify(repository).save(STUDENT_ENTITY);
	}

	@Test
	void deleteStudentShouldThrowStudentNotFoundExceptionWhenThereIsNoStudentForUuidb() {
		// given
		doReturn(true).when(repository).existsById(STUDENT_ID);

		// when
		underTest.deleteStudent(STUDENT_ID);

		// then
		verify(repository).deleteById(STUDENT_ID);
	}

	@Test
	void deleteStudentShouldDeleteFromDb() {
		// given
		doReturn(false).when(repository).existsById(STUDENT_ID);

		// when
		var actual = catchThrowable(() -> underTest.deleteStudent(STUDENT_ID));

		// then
		assertThat(actual).isInstanceOf(StudentNotFoundException.class)
			.hasMessage("student cannot be found with id: " + STUDENT_ID);
	}

	@Test
	void listStudentsShouldCallRepositoryAndMapTheResultToStudent() {
		// given
		doReturn(STUDENT).when(studentMapper).mapEntityToDto(STUDENT_ENTITY);
		doReturn(STUDENT_2).when(studentMapper).mapEntityToDto(STUDENT_ENTITY_2);
		doReturn(List.of(STUDENT_ENTITY, STUDENT_ENTITY_2)).when(repository).findAll();

		// when
		var actual = underTest.listStudents();

		// then
		assertThat(actual).containsOnly(STUDENT, STUDENT_2);
	}

	@Test
	void updateStudentShouldSaveToDbTheNewValueAndReturnWithSavedValueAsDto() {
		// given
		doReturn(true).when(repository).existsById(STUDENT_ID);
		doReturn(STUDENT_ENTITY).when(studentMapper).mapCreateDtoAndIdToEntity(STUDENT_ID, STUDENT_CREATE_DTO);
		doReturn(STUDENT_ENTITY).when(repository).save(STUDENT_ENTITY);
		doReturn(STUDENT).when(studentMapper).mapEntityToDto(STUDENT_ENTITY);

		// when
		var actual = underTest.updateStudent(STUDENT_ID, STUDENT_CREATE_DTO);

		// then
		assertThat(actual).isEqualTo(STUDENT);
		verify(repository).save(STUDENT_ENTITY);
	}

	@Test
	void updateStudentShouldThrowStudentNotFoundExceptionWhenThereIsNoStudentForUuidb() {
		// given
		doReturn(false).when(repository).existsById(STUDENT_ID);

		// when
		var actual = catchThrowable(() -> underTest.updateStudent(STUDENT_ID, STUDENT_CREATE_DTO));

		// then
		assertThat(actual).isInstanceOf(StudentNotFoundException.class)
			.hasMessage("student cannot be found with id: " + STUDENT_ID);
	}

}
