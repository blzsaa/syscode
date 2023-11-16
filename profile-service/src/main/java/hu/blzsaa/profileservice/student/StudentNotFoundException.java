package hu.blzsaa.profileservice.student;

import java.util.UUID;

public class StudentNotFoundException extends RuntimeException {

	public StudentNotFoundException(UUID uuid) {
		super("student cannot be found with id: " + uuid);
	}

}
