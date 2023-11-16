package hu.blzsaa.profileservice.student;

import hu.blzsaa.profileservice.server.api.StudentsApiDelegate;
import hu.blzsaa.profileservice.model.Student;
import hu.blzsaa.profileservice.model.StudentCreateDto;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentsApiDelegateImpl implements StudentsApiDelegate {

	private final StudentService studentService;

	StudentsApiDelegateImpl(StudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public ResponseEntity<Student> getStudentById(UUID studentId) {
		log.info("Incoming getStudentById request with id: {}", studentId);
		Student student = studentService.getStudentById(studentId);
		log.info("Returning student: {}", student);
		return ResponseEntity.ok(student);
	}

	@Override
	public ResponseEntity<Void> createStudent(StudentCreateDto studentCreateDto) {
		log.info("Incoming createStudent request with studentCreateDto: {}", studentCreateDto);
		Student student = studentService.createStudent(studentCreateDto);
		log.info("New student was created: {}", student);
		return ResponseEntity.created(URI.create("/students/" + student.getId())).build();
	}

	@Override
	public ResponseEntity<Void> deleteStudent(UUID studentId) {
		log.info("Incoming deleteStudent request with id: {}", studentId);
		studentService.deleteStudent(studentId);
		log.info("Deleted student with id: {}", studentId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Student>> listStudents() {
		log.info("Incoming listStudents request");
		List<Student> students = studentService.listStudents();
		log.info("Returning listStudents with {} elements", students.size());
		return ResponseEntity.ok(students);
	}

	@Override
	public ResponseEntity<Student> updateStudent(UUID studentId, StudentCreateDto studentCreateDto) {
		log.info("Incoming updateStudent request with id: {} and studentCreateDto: {}", studentId, studentCreateDto);
		Student student = studentService.updateStudent(studentId, studentCreateDto);
		log.info("Returning updated student {}", student);
		return ResponseEntity.ok(student);
	}

}
