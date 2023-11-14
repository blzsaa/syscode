package hu.blzsaa.syscodeprofileservice.student;

import hu.blzsaa.syscodeprofileservice.api.StudentsApiDelegate;
import hu.blzsaa.syscodeprofileservice.model.Student;
import hu.blzsaa.syscodeprofileservice.model.StudentCreateDto;
import java.net.URI;
import java.util.List;
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

	@Override
	public ResponseEntity<Void> createStudent(StudentCreateDto studentCreateDto) {
		return ResponseEntity.created(URI.create("/students/" + studentService.createStudent(studentCreateDto).getId()))
			.build();
	}

	@Override
	public ResponseEntity<Void> deleteStudent(UUID studentId) {
		studentService.deleteStudent(studentId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Student>> listStudents() {
		return ResponseEntity.ok(studentService.listStudents());
	}

}
