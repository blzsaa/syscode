package hu.blzsaa.syscodeprofileservice.student;

import hu.blzsaa.syscodeprofileservice.api.StudentsApiDelegate;
import hu.blzsaa.syscodeprofileservice.model.Student;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentsApiDelegateImpl implements StudentsApiDelegate {

	private final StudentService studentService;

	StudentsApiDelegateImpl(StudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public ResponseEntity<Student> getStudentById(UUID studentId) {
		Student student = studentService.getStudentById(studentId);
		return ResponseEntity.ok(student);
	}

}
