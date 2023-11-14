package hu.blzsaa.syscodeprofileservice.student;

import hu.blzsaa.syscodeprofileservice.model.Student;
import hu.blzsaa.syscodeprofileservice.model.StudentCreateDto;
import java.util.UUID;

final class TestUtils {

	private TestUtils() {
	}

	public static final UUID STUDENT_ID = UUID.randomUUID();

	public static final Student STUDENT = new Student(STUDENT_ID, "name", "emailAddress");

	public static final StudentCreateDto STUDENT_CREATE_DTO = new StudentCreateDto("name", "emailAddress");

	public static final StudentEntity STUDENT_ENTITY = new StudentEntity(STUDENT_ID, "name", "emailAddress");

}
