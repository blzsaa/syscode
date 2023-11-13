package hu.blzsaa.syscodeprofileservice.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hu.blzsaa.syscodeprofileservice.model.Student;
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
	void mapShouldMappAllFieldOfTheEntityToTheDto() {
		// given
		UUID uuid = UUID.randomUUID();
		StudentEntity studentEntity = new StudentEntity(uuid, "name", "emailAddress");

		// when
		var actual = underTest.map(studentEntity);

		// then
		assertThat(actual).isEqualTo(new Student(uuid, "name", "emailAddress"));
	}

}
