package hu.blzsaa.profileservice.student;

import static hu.blzsaa.profileservice.student.TestUtils.STUDENT_ID;
import static org.assertj.core.api.Assertions.assertThat;

import hu.blzsaa.profileservice.model.Student;
import hu.blzsaa.profileservice.model.StudentCreateDto;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentMapperTest {

	private StudentMapper underTest;

	@BeforeEach
	void setUp() {
		underTest = new StudentMapperImpl();
	}

	@Test
	void mapEntityToDtoShouldMapAllFields() {
		// given
		StudentEntity studentEntity = new StudentEntity(STUDENT_ID, "name", "emailAddress");

		// when
		var actual = underTest.mapEntityToDto(studentEntity);

		// then
		assertThat(actual).isEqualTo(new Student(STUDENT_ID, "name", "emailAddress"));
	}

	@Test
	void mapCreateDtoToEntityShouldMapAllFields() {
		// given
		StudentCreateDto studentCreateDto = new StudentCreateDto("name", "emailAddress");

		// when
		var actual = underTest.mapCreateDtoToEntity(studentCreateDto);

		// then
		assertThat(actual).usingRecursiveComparison().isEqualTo(new StudentEntity(null, "name", "emailAddress"));
	}

	@Test
	void mapCreateDtoAndIdToEntityShouldMapAllFields() {
		// given
		StudentCreateDto studentCreateDto = new StudentCreateDto("name", "emailAddress");

		// when
		var actual = underTest.mapCreateDtoAndIdToEntity(STUDENT_ID, studentCreateDto);

		// then
		assertThat(actual).isEqualTo(new StudentEntity(STUDENT_ID, "name", "emailAddress"));
	}

}
