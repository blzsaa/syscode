package hu.blzsaa.profileservice.student;

import hu.blzsaa.profileservice.api.StudentsApiDelegate;
import hu.blzsaa.profileservice.model.Student;
import hu.blzsaa.profileservice.model.StudentCreateDto;
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

	@Override
	public ResponseEntity<Student> updateStudent(UUID studentId, StudentCreateDto studentCreateDto) {
		return ResponseEntity.ok(studentService.updateStudent(studentId, studentCreateDto));
	}

}
