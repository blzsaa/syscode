package hu.blzsaa.syscodeprofileservice.student;

import hu.blzsaa.syscodeprofileservice.model.Student;
import hu.blzsaa.syscodeprofileservice.model.StudentCreateDto;
import java.net.URI;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
class StudentService {

	private final StudentRepository studentRepository;

	private final StudentMapper studentMapper;

	public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
		this.studentRepository = studentRepository;
		this.studentMapper = studentMapper;
	}

	public Student getStudentById(UUID studentId) {
		return studentRepository.findById(studentId).map(studentMapper::map).orElseThrow();
	}

	public Student createStudent(StudentCreateDto studentCreateDto) {
		StudentEntity student = studentMapper.map(studentCreateDto);
		return studentMapper.map(studentRepository.save(student));
	}

	public void deleteStudent(UUID studentId) {
		studentRepository.deleteById(studentId);
	}

}
